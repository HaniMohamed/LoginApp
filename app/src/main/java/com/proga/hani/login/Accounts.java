package com.proga.hani.login;

/**
 * Created by hani on 11/05/17.
 */

public class Accounts {

    int _id;
    String _name;
    String _password;
    String _phone_number;
    String _email;

    public Accounts() {
    }

    public Accounts(int id, String name, String password, String _phone_number, String email) {
        this._id = id;
        this._name = name;
        this._password = password;
        this._phone_number = _phone_number;
        this._email = email;

    }

    public Accounts(String name, String password, String _phone_number, String email) {
        this._name = name;
        this._password = password;
        this._phone_number = _phone_number;
        this._email = email;

    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getPassword() {
        return this._password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getPhoneNumber() {
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this._phone_number = phone_number;
    }

    public String getEmail() {
        return this._email;
    }

    public void setEmail(String email) {
        this._email = email;
    }
}