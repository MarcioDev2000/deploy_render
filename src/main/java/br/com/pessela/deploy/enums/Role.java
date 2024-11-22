package br.com.pessela.deploy.enums;

public enum Role {
    LOCADOR,
    ADMIN,
    LOCATARIO;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}