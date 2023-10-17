/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.SQLException;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author wiem
 */
public interface PromotionCRUD<T> {

    public void create(T promotion) throws SQLException;

    public T getOneById(int id) throws SQLException;

    public void update(T promotion, int id) throws SQLException;

    public void delete(int id) throws SQLException;

    public void deleteAll() throws SQLException;

    public List<T> getAll() throws SQLException;
}
