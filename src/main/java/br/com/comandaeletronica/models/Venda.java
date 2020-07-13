/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comandaeletronica.models;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vinic
 */
@Entity
@Table(name = "venda", schema = "public")
@NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v")
public class Venda implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String observacao;

    @NotNull
    private Float valorTotal;

    @ManyToOne
    private Comanda comandas;

    @ManyToOne
    private Cliente clientes;

    @ManyToOne
    private Funcionario funcionarios;

    public Venda() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Comanda getComandas() {
        return comandas;
    }

    public void setComandas(Comanda comandas) {
        this.comandas = comandas;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    public Funcionario getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionario funcionarios) {
        this.funcionarios = funcionarios;
    }

    
    
}
