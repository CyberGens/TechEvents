/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IService<S> {
    void insertPST(S t);
    void delete(int id);
    void update(S t);
    List<S> getAll();
    S getById(int id);
}
