package models.weapons;

import models.Projectile;

public abstract class Weapon {

    
    protected String name;
    protected int damage;
    protected double projectileSpeed;
    
    
    protected long cooldownTime; 
    private long lastShotTime;   
    protected int maxAmmo;
    protected int currentAmmo;
    protected boolean isReloading = false;
    protected long reloadStartTime = 0; 
    protected static final long RELOAD_DURATION_MS = 3000; 

    public Weapon(String name, int damage, double projectileSpeed, 
                  long cooldownTime, int maxAmmo) {
        this.name = name;
        this.damage = damage;
        this.projectileSpeed = projectileSpeed;
        this.cooldownTime = cooldownTime;
        this.maxAmmo = maxAmmo;
        this.currentAmmo = maxAmmo;
        this.lastShotTime = 0;
    }

    

    public Projectile shoot(double x, double y, double dirX, double dirY) {
        if (canShoot()) {
            
            Projectile projectile = createProjectile(x, y, dirX, dirY);
            if (projectile != null) {
                lastShotTime = System.currentTimeMillis();
                currentAmmo--;
                return projectile;
            }
        }
        return null; 
    }

    public boolean canShoot() {
        if (isReloading) return false;
        if (currentAmmo <= 0) {
            if (currentAmmo == 0 && !isReloading) {
                reload();
            }
            return false;
        }
        
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastShotTime) >= cooldownTime;
    }

   
    public abstract Projectile createProjectile(double x, double y, double dirX, double dirY);

    public void startReload() {
        if (!isReloading && currentAmmo < maxAmmo) {
            isReloading = true;
            reloadStartTime = System.currentTimeMillis();
            System.out.println("Reloading " + name + "... (3 seconds)");
        }
    }

    public void updateReload() {
        if (isReloading) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - reloadStartTime >= RELOAD_DURATION_MS) {
                completeReload();
            }
        }
    }

    public void completeReload() {
        currentAmmo = maxAmmo;
        isReloading = false;
        reloadStartTime = 0;
        System.out.println(name + " reload complete!");
    }

    public void reload() {
        startReload();
    }

   
    public int getAmmo() { return currentAmmo; }
    public int getMaxAmmo() { return maxAmmo; }
    public boolean isReloading() { return isReloading; }
    public String getName() { return name; }
    public int getDamage() { return damage; }
    public double getProjectileSpeed() { return projectileSpeed; }
    public long getReloadStartTime() { return reloadStartTime; }
    
  
    public double getReloadProgress() {
        if (!isReloading) return 0.0;
        long elapsed = System.currentTimeMillis() - reloadStartTime;
        return Math.min(1.0, (double) elapsed / RELOAD_DURATION_MS);
    }
}
