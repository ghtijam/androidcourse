package com.majithg.androidcourse;

/**
 * Created by majithg on 08-Apr-16.
 */
public class Products {

    private int _id;
    private String _productname;

    public Products(int _id) {
        this._id = _id;
    }

    public Products( String _productname) {
//        this._id = _id;
        this._productname = _productname;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_productname() {
        return _productname;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }
}
