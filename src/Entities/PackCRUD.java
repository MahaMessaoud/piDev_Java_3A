/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wiem
 */
public interface PackCRUD<T> {

    public void create(T pack) throws SQLException;

    public T getOneById(int id) throws SQLException;

    public void update(T pack, int id) throws SQLException;

    public void delete(int id) throws SQLException;

    public void deleteAll() throws SQLException;

    public List<T> getAll() throws SQLException;
}
