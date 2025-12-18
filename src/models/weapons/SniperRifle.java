package models.weapons;

import models.Projectile;

public class SniperRifle extends Weapon {

    public SniperRifle() {
        super("Sniper Rifle", 50, 25.0, 1500, 5);
    }

    @Override
    public Projectile createProjectile(double x, double y, double dirX, double dirY) {
        return new Projectile(x, y, dirX, dirY, this.projectileSpeed, this.damage);
    }
}
