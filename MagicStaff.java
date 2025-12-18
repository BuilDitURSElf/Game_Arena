package models.weapons;

import models.Projectile;

public class MagicStaff extends Weapon {

    public MagicStaff() {
        super("Magic Staff", 30, 15.0, 800, 20);
    }

    @Override
    public Projectile createProjectile(double x, double y, double dirX, double dirY) {
        return new Projectile(x, y, dirX, dirY, this.projectileSpeed, this.damage);
    }
}