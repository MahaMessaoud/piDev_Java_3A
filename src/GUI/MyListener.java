/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Entities.Cours;
import Entities.Pack;
import Entities.Reclamation;
import Entities.Planning;

/**
 *
 * @author wiem
 */
public interface MyListener {
    public void onClickListener(Pack pack);
    public void onClickListener(Activite act);
    public void onClickListener(Cours cours);
    public void onClickListener(Planning p);
    public void onClickListener(Reclamation rec);
}
