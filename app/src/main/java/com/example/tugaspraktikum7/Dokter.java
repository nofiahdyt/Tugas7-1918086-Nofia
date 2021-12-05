package com.example.tugaspraktikum7;

public class Dokter {
    private String _id, _nama, _sp;
    public Dokter (String id, String nama, String sp) {
        this._id = id;
        this._nama = nama;
        this._sp = sp;
    }
    public Dokter() {
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String get_nama() {
        return _nama;
    }
    public void set_nama(String _nama) {
        this._nama = _nama;
    }
    public String get_sp() {
        return _sp;
    }
    public void set_sp(String _sp) {
        this._sp = _sp;
    }
}
