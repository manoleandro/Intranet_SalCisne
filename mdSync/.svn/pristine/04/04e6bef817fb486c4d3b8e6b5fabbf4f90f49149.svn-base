package com.mdSync.exception;

import java.util.Date;

public class JPAException extends Exception {
    public JPAException(String mensage) {
        super(mensage);
    }

    public JPAException(Class classe) {
        if (classe != null) {
            System.out.println("%%% " + new Date() + " JPAException (" + classe.getName() + ") %%% ");
        } else {
            System.out.println("%%% " + new Date() + " JPAException %%% ");
        }
    }

    public JPAException(Class classe, Throwable e) {
        if (classe != null) {
            System.out.println("%%% " + new Date() + " JPAException (" + classe.getName() + ") %%% ");
        } else {
            System.out.println("%%% " + new Date() + " JPAException %%% ");
        }
        e.printStackTrace();
    }

    public void printError(Class classe, Throwable e) {
        if (classe != null) {
            System.out.println("%%% " + new Date() + " JPAException (" + classe.getName() + ") %%% ");
        } else {
            System.out.println("%%% " + new Date() + " JPAException %%% ");
        }
        e.printStackTrace();
    }
}

