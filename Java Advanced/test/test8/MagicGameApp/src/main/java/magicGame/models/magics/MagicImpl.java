package magicGame.models.magics;

import magicGame.common.ExceptionMessages;

public abstract class MagicImpl implements Magic {
    private String name;
    private int bulletsCount;

    protected MagicImpl(String name, int bulletsCount) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_NAME);
        }
        if (bulletsCount < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_BULLETS_COUNT);
        }
        this.name = name;
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBulletsCount() {
        return bulletsCount;
    }

    @Override
    public int fire() {
        return 0;
    }
}
