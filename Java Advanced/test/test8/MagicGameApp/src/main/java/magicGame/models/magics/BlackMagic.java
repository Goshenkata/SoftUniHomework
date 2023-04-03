package magicGame.models.magics;

public class BlackMagic extends MagicImpl{
    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }
    private int bullets = getBulletsCount();

    @Override
    public int fire() {
        if (bullets - 10 >= 0) {
            bullets-=10;
            return 10;
        }
        bullets = 0;
        return 0;
    }
}
