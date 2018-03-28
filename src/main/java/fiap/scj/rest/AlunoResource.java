package fiap.scj.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//http://localhost:8080/rest/aluno
import javax.ws.rs.core.Response;

@Path("aluno") // @Path = em que url/raiz ficara o recurso
public class AlunoResource{

	private static List<Aluno> listaDeAlunos = new ArrayList<Aluno>();
	static {
		listaDeAlunos.add(new Aluno(1,"Tobias"));
		listaDeAlunos.add(new Aluno(2,"Jeremias"));
		listaDeAlunos.add(new Aluno(3,"Josias"));
	}
	
	// xml eh o retorno padrao
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Aluno> getAlunos() {
		return listaDeAlunos;
	}
	
	/*
	 JSON:{
        "nome": "Tobias",
        "ra": "1"
    }
	XML:<aluno>
    	<nome>Tobias</nome>
    	<ra>1</ra>
	</aluno>
	*/
	// para escolher como enviar o Aluno: content-type application/xml ou json
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createAluno(Aluno aluno) throws Exception{
		listaDeAlunos.add(aluno);
		return Response.created(
							new URI("http://8080/rest/aluno/"+aluno.getRa())
						).build();
		
	}
	
	
	
	// PathParam
	// http://localhost:8080/rest/aluno/1
	// para escolher tipo do retorno Accept: application/xml ou json
	@GET
	@Path("/{ra}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Aluno findAluno(@PathParam(value = "ra") Integer ra) {
		return findAlunoByRa(ra);
	}
	
	// QueryParam
	// http://localhost:8080/rest/aluno/busca?ra=1
	// para escolher tipo do retorno Accept: application/xml ou json
	@GET
	@Path("/busca")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Aluno findAlunoByQuery(@QueryParam("ra") Integer ra) {
		return findAlunoByRa(ra);
	}
	
	private Aluno findAlunoByRa(int ra) {
		for (Aluno aluno : listaDeAlunos) {
			if (aluno.getRa() == ra) 
				return aluno;
		}
		return null;
	}
	
	
}