package models.characters;

import models.weapons.MagicStaff;
import models.weapons.Pistol;
import models.weapons.SniperRifle;

public class Sniper extends Fighter {

    public Sniper(double x, double y) {
        
        super(x, y, 40, 40, 70, 3.5);
        
        
        this.addWeapon(new SniperRifle());
        this.setWeapon(new MagicStaff());
        this.addWeapon(new Pistol());
    }

    
    public void update() {
        
        super.update();
    }
}