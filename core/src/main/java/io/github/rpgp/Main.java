package io.github.rpgp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Sprite player;
    private FitViewport viewport;
    Vector2 touchPos;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("charmander.png");
        player = new Sprite(image);
        player.setSize(1, 1);
        viewport = new FitViewport(8, 5);
        touchPos = new Vector2();
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        player.draw(batch);

        batch.end();
    }

    private void logic() {

    }

    private void input() {
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.translateX(speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.translateX(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.translateY(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.translateY(speed * delta);
        }
        if (Gdx.input.isTouched()) { // If the user has clicked or tapped the screen
            touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get where the touch happened on screen
            viewport.unproject(touchPos); // Convert the units to the world units of the viewport
            player.setCenter(touchPos.x, touchPos.y); // Change the centered position of the player
        }
    }
}
