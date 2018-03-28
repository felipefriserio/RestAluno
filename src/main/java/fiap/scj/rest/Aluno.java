package fiap.scj.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Aluno {

	public Aluno(){}
	
	public Aluno(int ra, String nome) {
		this.nome = nome;
		this.ra = ra;
	}
	
	private String nome;
	private int ra;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getRa() {
		return ra;
	}
	public void setRa(int ra) {
		this.ra = ra;
	}
}
