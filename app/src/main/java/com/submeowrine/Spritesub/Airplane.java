package com.submeowrine.Spritesub;

import android.graphics.Bitmap;

import static com.submeowrine.Spritesub.Direction.LEFT_TO_RIGHT;
import static com.submeowrine.Spritesub.Direction.RIGHT_TO_LEFT;
import static com.submeowrine.Spritesub.Size.MEDIUM;
import static com.submeowrine.Spritesub.Size.SMALL;

public class Airplane extends Enemy {

    private static float minY, maxY;
    private float y;

    /**
     * The default constructor. Loads the correct plane picture for its size
     */
    public Airplane() {
        super();
        this.size = size;
//        switch (size) {
//            case SMALL:
//                image = BitmapFactory.decodeResource(res, R.drawable.little_airplane);
//                break;
//            case MEDIUM:
//                image = BitmapFactory.decodeResource(res, R.drawable.medium_airplane);
//                break;
//            case LARGE:
//            default:
//                image = BitmapFactory.decodeResource(res, R.drawable.big_airplane);
//                break;
//        }
//        //planes move left (negative X velocity)
        if (direction == RIGHT_TO_LEFT) {
            image = ImageCache.getAirplaneImage(size, RIGHT_TO_LEFT);
            bounds.set(0,0,image.getWidth(), image.getHeight());
            velocity.x = -1;
            velocity.x = getRandomVelocity();

        } else {
            image = ImageCache.getAirplaneImage(size, LEFT_TO_RIGHT);
            bounds.set(0,0,image.getWidth(), image.getHeight());
            velocity.x = 1;
            velocity.x = getRandomVelocity();
        }
    }

    /**
     * overriding of the reset method for Airplane
     */
    @Override
    public void reset() {
        super.reset();
        y = (float)(minY + (maxY-minY)*Math.random());
        if (direction == RIGHT_TO_LEFT) {
            image = ImageCache.getAirplaneImage(size, RIGHT_TO_LEFT);
            bounds.offsetTo(ImageCache.screenWidth(), y);
            velocity.x = -1;
            velocity.x = getRandomVelocity();

        } else {
            image = ImageCache.getAirplaneImage(size, LEFT_TO_RIGHT);
            bounds.offsetTo(-bounds.width(), y);
            velocity.x = 1;
            velocity.x = getRandomVelocity();
        }
    }

    /**
     * Setter method intended to be called by the View subclass, to set the upper and lower range
     * of this sprite's Y coordinates. The caller may pass either end of the range as either the
     * first or second parameter, and the method will put the smaller value in minY and the larger
     * in maxY.
     * @param y1 one end of the range
     * @param y2 the other end of the range
     */
    public static void setSkyLimits(float y1, float y2) {
        minY = Math.min(y1, y2);
        maxY = Math.max(y1, y2);
    }

//    @Override
//    protected float relativeWidth() {
//        switch (size) {
//            case SMALL:
//                return 0.05f;
//            case MEDIUM:
//                return 0.083f;
//            case LARGE:
//            default:
//                return 0.12f;
//        }
//    }

    /**
     * modification of move method
     */
    @Override
    public void move() {
        super.move();
        if (bounds.left > ImageCache.screenWidth() || bounds.right < 0) {
            y = (float)(minY + (maxY-minY)*Math.random());
            if (direction == LEFT_TO_RIGHT) {
                reset();
                image = ImageCache.getAirplaneImage(size, LEFT_TO_RIGHT);
                bounds.set(0,0,image.getWidth(), image.getHeight());
                bounds.offsetTo(-bounds.width(), y);
            } else {
                reset();
                image = ImageCache.getAirplaneImage(size, RIGHT_TO_LEFT);
                bounds.set(0,0,image.getWidth(), image.getHeight());
                bounds.offsetTo(ImageCache.screenWidth(), y);
            }
        }
    }

    /**
     * we have to override this since we are extending the Enemy class
     * @return
     */
    @Override
    public Bitmap getExplodingImage(){
        return ImageCache.getAirplaneExposion();
    }

    /**
     * overrided to get points for airplanes
     * @return
     */
    @Override
    public int getPointValue() {
        if (size == SMALL) {
            return  75;
        }
        if (size == MEDIUM) {
            return 20;
        }
        else {
            return 15;
        }
    }
}
