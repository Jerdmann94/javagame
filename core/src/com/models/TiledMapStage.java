package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.cards.BasicMoveCard;

import java.util.ArrayList;
import java.util.List;


public class TiledMapStage extends Stage {
    List<TiledMapActor> actors = new ArrayList<>();

    public TiledMapStage(TiledMap tiledMap, Character player, OrthographicCamera camera, float w, float h) {
        super(new ScalingViewport(Scaling.stretch,w,h,camera));
        this.tiledMap = tiledMap;
        this.player = player;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public Character player;
    private TiledMap tiledMap;


    public TiledMapStage(TiledMap tiledMap, Character player) {
        this.tiledMap = tiledMap;
        this.player = player;





    }
    public void createButtonsForMap(){
        for (MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer(tiledLayer);
        }
    }
    private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
        int counter = 0;
        Texture img = new Texture("testtile.png");
        Texture backimg = new Texture("walltiles.png");
        Texture selectable = new Texture("animals.png");
        TextureRegion region  = new TextureRegion(img,64,64);
        TextureRegion backregion = new TextureRegion(backimg,64,64);
        TextureRegion selectregion = new TextureRegion(selectable,64,64);
        for (int x = 0; x < tiledLayer.getWidth(); x++) {
            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                counter++;
                int posX = x * tiledLayer.getTileWidth()+(Gdx.graphics.getWidth()/2)-320;
                int posY = y * tiledLayer.getTileHeight()+(Gdx.graphics.getHeight()/2)-80;

                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);

                cell.setTile(new StaticTiledMapTile(region));
                cell.getTile().getProperties().put("default",region);
                cell.getTile().getProperties().put("selected",backregion);
                cell.getTile().getProperties().put("selectable", selectregion);
                cell.getTile().getProperties().put("x",posX);
                cell.getTile().getProperties().put("y",posY);
                TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell);
                actor.setBounds(posX,posY, tiledLayer.getTileWidth(),
                        tiledLayer.getTileHeight());

                addActor(actor);
                cell.getTile().setId(counter);

                if(player.getSelectedCard()!= null){
                    if (player.getSelectedCard().tileCheck(actor)){
                        EventListener eventListener = new TiledMapClickListener(actor,player);
                        actor.addListener(eventListener);
                        actor.getCell().getTile().setTextureRegion((TextureRegion) cell.getTile().getProperties().get("selectable"));
                        player.getPossibleTargets().add(actor.getCell());
                    }
                }


                actors.add(actor);


            }
            System.out.println(player.getTargets().size());
        }
        for (int x = 0; x < tiledLayer.getWidth(); x++) {
            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                int posX = x * tiledLayer.getTileWidth()+(Gdx.graphics.getWidth()/2)-320;
                int posY = y * tiledLayer.getTileHeight()+(Gdx.graphics.getHeight()/2)-80;
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);

            }
        }
    }
    public void removeActors(){
        actors.forEach(actor -> {
            actor.remove();
        });
    }
}