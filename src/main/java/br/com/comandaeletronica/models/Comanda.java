/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comandaeletronica.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vinic
 */
@Entity
@Table(name = "comanda", schema = "public")
@NamedQuery(name = "Comanda.findAll", query = "SELECT c FROM Comanda c")
public class Comanda implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(name = "numero_comanda", unique = true)    
    private Integer numero;

    @NotNull
    private Integer qtde;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "comanda_produto", schema = "public", joinColumns = {
        @JoinColumn(name = "comanda_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "produto_id")})
    private List<Produto> produtos;
    
    @OneToMany(mappedBy = "comandas")    
    @JsonIgnore
    private List<Venda> venda;
    
  
    public Comanda() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Venda> getVenda() {
        return venda;
    }

    public void setVenda(List<Venda> venda) {
        this.venda = venda;
    }        
}
