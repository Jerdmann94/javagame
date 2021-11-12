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
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
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
	public static Skin skin = new Skin();
	static Character player;
	static TiledMapStage stageMap;
	static VisTable handTable;
	static Table rootTable;
	static TextButton confirm;

	static ArrayList<CardButton> cardButtons;

	
	@Override
	public void create () {


		VisUI.load();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();


		camera = new OrthographicCamera();




		createSkinFormat();

		createMap();
		createPlayer();
//		stageMap = new TiledMapStage(tiledMap,player,camera,w,h);
		stageMap = new TiledMapStage(tiledMap,player);

		stageMap.addActor(rootTable);

		Gdx.input.setInputProcessor(stageMap);

		stageMap.setDebugAll(true);
		createButtons();

		stageMap.createButtonsForMap();
		player.setX((int)stageMap.getActors().get(2).getX());
		player.setY((int)stageMap.getActors().get(2).getY());



		camera.setToOrtho(false,w,h);
		camera.translate(new Vector2( -(Gdx.graphics.getWidth()/2) + 320,-(Gdx.graphics.getHeight()/2) + 80) );



	}



	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
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


//		//SKIN STUFF
//		Pixmap pixmap = new Pixmap(1, 1, com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888);
//		pixmap.setColor(Color.WHITE);
//		pixmap.fill();
//		skin.add("white", new Texture(pixmap));
//
//		skin.add("default", new BitmapFont());
//
//		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
//		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
//		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
//		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
//		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
//		textButtonStyle.font = skin.getFont("default");
//		skin.add("default", textButtonStyle);

		skin = new Skin(Gdx.files.internal("uiskin.json"));

	}

	private void createButtons() {

		handTable = new VisTable(true);
		cardButtons = new ArrayList<CardButton>();
		for (int i = 0; i < player.getHand().size(); i++) {
			cardButtons.add(new CardButton("Click me!", "default",player.getHand().get(i)));
            player.getHand().get(i).setButton(cardButtons.get(i));

			EventListener eventListener = new CardListener(player);
			cardButtons.get(i).addListener(eventListener);

			cardButtons.get(i).setHeight(250);
			cardButtons.get(i).setWidth(150);
			cardButtons.get(i).addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {

					CardButton butt = (CardButton)actor;
					player.setSelectedCard(butt.getCard());


                    stageMap.createButtonsForMap();
					createConfirm();
				}
			});

			//SET CARD LABELS



		}

		buildHandTable();


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
		//sprite.setPosition((stageMap.getActors().get(2).getX()), (stageMap.getActors().get(2).getY()));
		player = new Character((0),(0),sprite,100);

	}

	public static void createConfirm(){
		if(player.getTargets().size() == player.getSelectedCard().targets){
			confirm = new TextButton("Confirm",skin);

			confirm.setHeight(250);
			confirm.setWidth(150);
			confirm.setPosition(0,0);
			confirm.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {

					TextButton butt = (TextButton)actor;
					butt.setText("Clicked");
					cardButtons.remove(player.getSelectedCard().getCardButton());
					player.getSelectedCard().playCard();
					player.resetCells();
					removeConfirm();
					buildHandTable();

				}
			});
			rootTable.addActor(confirm);
		}

	}

	private static void removeConfirm() {
		confirm.remove();
		stageMap.removeActors();
	}
	private static void buildHandTable(){
		handTable.clearChildren();
		handTable.remove();
		handTable.pack();
		handTable.pad(25);


		cardButtons.forEach(button -> {
			handTable.add(button).height(button.getHeight()).width(button.getWidth()).space(10f);



		});



		rootTable.add(handTable);
		rootTable.bottom();

	}

}
