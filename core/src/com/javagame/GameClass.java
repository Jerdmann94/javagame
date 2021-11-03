package com.javagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.command.*;
import com.models.*;
import com.models.Character;

import java.util.ArrayList;


public class GameClass extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion region;
	Sprite sprite;
	OrthographicCamera camera;
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;


	Skin skin;
	ArrayList<CardButton> buttons;

	Character player;
	MapObjects mapObjects = new MapObjects();
	TiledMapStage stageMap;
	Table table;
	Table rootTable;

	TextButton confirm;



	
	@Override
	public void create () {



		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();


		camera = new OrthographicCamera();


		skin = new Skin();

		createSkinFormat();

		createMap();
		createPlayer();
//		stageMap = new TiledMapStage(tiledMap,player,camera,w,h);
		stageMap = new TiledMapStage(tiledMap,player);

		stageMap.addActor(rootTable);

		Gdx.input.setInputProcessor(stageMap);

		stageMap.setDebugAll(true);
		createButtons();



		camera.setToOrtho(false,w,h);
		camera.translate(new Vector2( -(Gdx.graphics.getWidth()/2) + 320,-(Gdx.graphics.getHeight()/2) + 80) );



	}



	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		batch.begin();
		player.getSprite().draw(batch);
		batch.end();
		stageMap.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stageMap.draw();
		Gdx.gl.glClearColor(0,0,0, 1);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
		tiledMap.dispose();
	}
	private void createSkinFormat() {

		// TABLE STUFF
		rootTable = new Table();

		rootTable.setFillParent(true);


		//SKIN STUFF
		Pixmap pixmap = new Pixmap(1, 1, com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
	}

	private void createButtons() {

		table = new Table();

		buttons = new ArrayList<CardButton>();
		for (int i = 0; i < player.getHand().size(); i++) {
			buttons.add(new CardButton("Click me!", skin,player.getHand().get(i)));
            player.getHand().get(i).setButton(buttons.get(i));

			EventListener eventListener = new CardListener(player);
			buttons.get(i).addListener(eventListener);

			buttons.get(i).setHeight(250);
			buttons.get(i).setWidth(150);


			//buttons.get(i).setPosition(0,0);

			table.add(buttons.get(i)).pad(10).width(150).height(250);







			buttons.get(i).addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {

					CardButton butt = (CardButton)actor;
					player.setSelectedCard(butt.getCard());

					butt.setText("Clicked");
                    stageMap.createButtonsForMap();
					createConfirm();
				}
			});

		}
		table.setPosition(Gdx.graphics.getWidth()/2,150 );
		rootTable.addActor(table);



	}
	private void createMap() {
		tiledMap = new TmxMapLoader().load("defaultgrid.tmx");

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

	}

	private void createPlayer() {
		img = new Texture("farmers.png");
		region = new TextureRegion(img,16,16);
		sprite = new Sprite(region,0, 0,16,16);
		sprite.setScale(4,4);
		sprite.setOrigin(0,0);
		sprite.setPosition((Gdx.graphics.getWidth()/2 - 160), (Gdx.graphics.getHeight()/2 - 320));
		player = new Character((Gdx.graphics.getWidth()/2 - 160),(Gdx.graphics.getHeight()/2 - 320),sprite,100);

	}

	private void createConfirm(){
		confirm = new TextButton("Confirm",skin);

		confirm.setHeight(250);
		confirm.setWidth(150);
		confirm.setPosition(100,100);
		confirm.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {

				TextButton butt = (TextButton)actor;
				butt.setText("Clicked");
				player.getSelectedCard().playCard();


//				table.removeActor(player.getSelectedCard().getActor());
//
//                table.pack();
				player.resetCells();
				removeConfirm();

			}
		});
		rootTable.addActor(confirm);
	}

	private void removeConfirm() {
		confirm.remove();
		stageMap.removeActors();
	}

}
