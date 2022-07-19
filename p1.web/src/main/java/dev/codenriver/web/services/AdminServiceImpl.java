package dev.codenriver.web.services;

import dev.codenriver.orm.data.DAO;
import dev.codenriver.web.models.Message;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService{
    DAO dao = new DAO();

    @Override
    public Message addMessage(Message msg){
       /* boolean stored;
        try{
            stored = true;
        } catch (SQLException e){

            return null;
        }*/
        return null;
    }

    @Override
    public Message editMessage(Message msg){
       /*
        if(){

        }*/
        return null;
    }
}
