package magicGame.models.magics;

public class RedMagic extends MagicImpl{
    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    private int bullets = getBulletsCount();
    @Override
    public int fire() {
        if (bullets - 1 >=0) {
            bullets-= 1;
            return 1;
        }
        bullets = 0;
        return 0;
    }
}
