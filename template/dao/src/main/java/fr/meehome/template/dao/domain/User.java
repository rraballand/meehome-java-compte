package fr.meehome.template.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMPLATE_USER")
public class User  {

    @Id
    @GeneratedValue
    @Column(name = "TEMPLATE_USER_ID")
    private int id;

    @Column(name = "TEMPLATE_USER_LOGIN")
    private String login;

    @Column(name = "TEMPLATE_USER_PASSWORD")
    private String password;
    
    @Column(name = "TEMPLATE_USER_NOM")
    private String nom;

	@Column(name = "TEMPLATE_USER_PRENOM")
    private String prenom;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
