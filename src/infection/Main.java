package infection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener, KeyListener, MouseMotionListener, MouseListener{

	public final static int SCREENX = 1200;
	public final static int SCREENY = 800;
	private Player player = new Player();
	private Crosshair crosshair = new Crosshair();
	private int  level = 1;
	private int dead = 0;
	private int kills = 0;
	private int bulletKills = 0;
	private int bullets = 0;
	private int bulletsAll = 0;
	private int blobCounter = 0;
	private int blobAmount = 1;
	private int furyCounter = 0;
	private int furyAmount = 3;
	private boolean begin = false;
	private boolean end = false;
	private boolean restart = false;
	private BodyArmor bArmor = new BodyArmor();
	private GrenadePickUp GPU = new GrenadePickUp();
	private Health healthPickUp = new Health();
	private LevelUp LU = new LevelUp();
	private boolean levelUpStart = true;
	private ArrayList<Enemy> elist = level();
	private ArrayList<Bullet> blist = new ArrayList();
	private ArrayList<Grenade> glist = new ArrayList();
	private ArrayList<Blood> bloodList = new ArrayList();
	private ArrayList<Blob> blobList = blobLevel();
	private ArrayList<Fury> flist = furyLevel();
	private static File music = new File("C:/Users/Anmol/Desktop/uoft/Year2/SecondSem/CSC207/Personal/GTABTH.WAV");
	private Blood mainScreenBlood = new Blood(SCREENX/2, SCREENY/2);
	
	public static void main(String[] args){
		JFrame window = new JFrame("INFECTION");
		Main game = new Main();
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		Timer t = new Timer(5, game); // 5; DisplayMode.REFRESH_RATE_UNKNOWN
		t.start();
		window.addKeyListener(game);
		window.addMouseMotionListener(game);
		window.addMouseListener(game);
		window.setFocusable(true);
		window.setFocusTraversalKeysEnabled(false);	
		PlaySound(music);
	}
	
	private static void PlaySound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e)
		{
		}
	}

        @Override
	public Dimension getPreferredSize(){
		return new Dimension(SCREENX, SCREENY);
	}
	
	@Override
	public void paintComponent(Graphics g){
		if (begin == false){
			newGame(g);
		}
		if (end == true && restart == true){
			newGame(g);
			reset();
		}
		if (end != true && begin != false){
			bulletsAll = bullets + blist.size();
			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, SCREENX, SCREENY);
			if (level == 1 && levelUpStart){
				LU.create(g, level);
				if (LU.size >= 200){
					LU = new LevelUp();
					levelUpStart = false;
				}
			}
			if (player.yCor <= 0 - player.size || player.yCor >= SCREENY + player.size || player.xCor <= 0 - player.size ||
					player.xCor >= SCREENX + player.size){
				player.relocate();
			}
			if (level > 20){
				healthPickUp.spawnRate = 5;
			}
			if (level > 40){
				healthPickUp.spawnRate = 3;
			}
			if (level % healthPickUp.spawnRate == 0 && healthPickUp.pickedUp == false){
				healthPickUp.create(g);
			}
			if (level >= 10 && GPU.pickedUp == false){
				GPU.collectable = true;
				GPU.create(g);
			}
			if (level >= 15){
				player.maxGrenade = 2;
			}
			if (level >= 18 && level % 2 == 0){
				blobAmount = 2;
			}
			if (level >= 20){
				player.maxGrenade = 3;
			}
			if (level >= 30){
				blobAmount = 3;
				furyAmount = 5;
				player.maxGrenade = 5;
			}
			if (level >= 40){
				blobAmount = 5;
				furyAmount = 7;
				player.maxGrenade = 6;
				player.maxHealth = 150;
			}
			if (level >= 50){
				blobAmount = 7;
				furyAmount = 10;
				player.maxGrenade = 7;
				player.maxHealth = 200;
			}
			if (level >= 55){
				blobAmount = 9;
				furyAmount = 12;
				player.maxGrenade = 8;
			}
			if (level >= 60){
				blobAmount = 12;
				furyAmount = 15;
				player.maxGrenade = 9;
			}
			if (level >= 70){
				blobAmount = 15;
				furyAmount = 18;
				player.maxGrenade = 10;
			}
			if (level >= 80){
				blobAmount = 20;
				furyAmount = 23;
				player.maxGrenade = 12;
			}
			if (player.detectCollision(GPU.xCor, GPU.yCor, GPU.size) && GPU.pickedUp == false && GPU.collectable){
				GPU.size = 0;
				GPU.pickedUp = true;
				player.grenadeCount = player.maxGrenade;
			}
			if (player.detectCollision(healthPickUp.xCor, healthPickUp.yCor, healthPickUp.sizeW - 3) && level % healthPickUp.spawnRate == 0){
				player.health = player.maxHealth;
				healthPickUp.pickedUp = true;
			}
			if (level >= 12 && bArmor.collected == false){
				bArmor.create(g);
			}
			if (player.detectCollision(bArmor.xCor, bArmor.yCor, bArmor.size) && bArmor.collectable){
				bArmor.size -= bArmor.size;
				bArmor.collected = true;
			}
			ArrayList<Enemy> enemyList = new ArrayList();
			ArrayList<Blob> cycleBlobList = new ArrayList();
			ArrayList<Fury> furyList = new ArrayList();
			
                        /*
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font
                        ){
				g.drawString("ACCURACY: " + Math.round((((float) bulletKills)/((float) bulletsAll))*100) + "%", SCREENX/2 - 80, SCREENY/2);
			}
                        */
			for (Blood blood: bloodList){
				blood.create(g);
			}
			for (Enemy e: elist){
				g.setColor(new Color(189, 54, 255));
				g.fillOval(e.xCor, e.yCor, e.size, e.size);	
				g.setColor(Color.BLACK);
				g.fillOval((int) (e.xCor + e.size/3.5), (int) (e.yCor + e.size/3.5), e.size/2, e.size/2);
				g.setColor(new Color(255, 255, 115));
				g.fillOval((int) (e.xCor + e.size/3.5), (int) (e.yCor + e.size/3.5), e.size/2, e.size/2);
				e.move();
				e.destX = player.xCor;
				e.destY = player.yCor;
				enemyList.add(e);
				if (player.detectCollision(e.xCor, e.yCor, e.size) == true && e.size > 0){
					player.health -= player.damageE;
					bloodList.add(new Blood(e.xCor, e.yCor));
					e.size -= e.size;
					dead += 1;
					kills += 1;
				}
			}
			for (Blob blob: blobList){
				g.setColor(new Color(255,105,180));
				g.fillOval(blob.xCor, blob.yCor, blob.size, blob.size);
				g.setColor(Color.BLACK);
				g.drawOval(blob.xCor + 1, blob.yCor + 1, blob.size - 1, blob.size - 1);
				blob.move();
				blob.destX = player.xCor;
				blob.destY = player.yCor;
				cycleBlobList.add(blob);
				if (player.detectCollision(blob.xCor, blob.yCor, blob.size) == true && blob.size > 0){
					player.health -= blob.size;
					blob.size = 0;
					kills += 1;
					bloodList.add(new Blood(blob.xCor, blob.yCor));
					blobCounter += 1;
				}
			}
			for (Fury f: flist){
				g.setColor(new Color(220,20,60));
				g.fillOval(f.xCor, f.yCor, f.size, f.size);	
				g.setColor(Color.BLACK);
				g.drawOval(f.xCor, f.yCor, f.size - 1 , f.size - 1);	
				f.move();
				f.destX = player.xCor;
				f.destY = player.yCor;
				furyList.add(f);
				if (player.detectCollision(f.xCor, f.yCor, f.size) == true && f.size > 0){
					player.health -= f.size;
					bloodList.add(new Blood(f.xCor, f.yCor));
					f.size -= f.size;
					kills += 1;
					furyCounter += 1;
				}
			}
			
			if (blist.size() > 0){
				for (Bullet i: blist){
					i.createBullet(g);
					for (Enemy e: enemyList){
						if (e.detectCollision(i.xCor, i.yCor, i.size) == true && i.size != 0 && e.size != 0){
							e.health -=50;
							i.size = 0;
                                                        bloodList.add(new Blood(e.xCor, e.yCor));
							if (e.health <= 0){
                                                                e.size = 0;
								dead += 1;
								kills += 1; 
								bulletKills += 1;
							}
						}
					}
					for (Blob blob: cycleBlobList){
						if (blob.detectCollision(i.xCor, i.yCor, i.size) == true && i.size != 0 && blob.size != 0){
							blob.size -= blob.reduction;
							bloodList.add(new Blood(blob.xCor, blob.yCor));
							i.size = 0;
							bulletKills += 1;
							if (blob.size <= 0){
								kills += 1;
								blobCounter += 1;
							}
						}
					}
					for (Fury f: furyList){
						if (f.detectCollision(i.xCor, i.yCor, i.size) == true && i.size != 0 && f.size != 0){
							f.size += 5;
							bloodList.add(new Blood(f.xCor, f.yCor));
							i.size = 0;
							bulletKills += 1;
							if (f.size > 60){
								f.size = 0;
								kills += 1;
								bloodList.add(new Blood(f.xCor, f.yCor));
								bulletKills += 1;
								furyCounter += 1;
							}
						}
					}
				}
			}
			for (Grenade grenade: glist){
				grenade.createGrenade(g);
				if (grenade.landed){
					grenade.explode(g);
					for (Enemy e: enemyList){
						if (e.detectCollision(grenade.xCor, grenade.yCor, grenade.explosion) == true && grenade.explosion != 0 && 
								grenade.size == 0 && e.size != 0){
							e.health -= 10;
							bloodList.add(new Blood(e.xCor, e.yCor));
							if (e.health <= 0){
								e.size = 0;
								dead += 1;
								kills += 1;
							}
						}
						if (player.detectCollision(grenade.xCor, grenade.yCor, grenade.explosion) == true && grenade.explosion != 0 && 
								grenade.size == 0 && grenade.isHit == false){
							player.health -= player.damageG;
							grenade.isHit = true;
						}
					}
					for (Blob blob: cycleBlobList){
						if (blob.detectCollision(grenade.xCor, grenade.yCor, grenade.explosion) == true && grenade.explosion != 0 && 
								grenade.size == 0 && blob.size != 0){
							blob.size -= blob.reduction;
							bloodList.add(new Blood(blob.xCor, blob.yCor));
							if (blob.size <= 0){
								kills += 1;
								blobCounter += 1;
							}
						}
					}
					for (Fury f: furyList){
						if (f.detectCollision(grenade.xCor, grenade.yCor, grenade.explosion) == true && grenade.explosion != 0 && 
								grenade.size == 0 && f.size != 0){
							f.health -= 10;
							bloodList.add(new Blood(f.xCor, f.yCor));
							if (f.health <= 0){
								f.size = 0;
								kills += 1;
								furyCounter += 1;
							}
						}
					}
				}
			}
			if (dead == level && blobCounter == blobList.size() && furyCounter == flist.size()){
				level += 1;
				LU.leveled = true;
				dead = 0;
				healthPickUp = new Health();
				if (player.health != player.maxHealth){
					player.health += 5;
				}
				bloodList = new ArrayList();
				if (GPU.pickedUp){
					player.grenadeCount = player.maxGrenade;
				}
				bullets += blist.size();
				blist = new ArrayList();
				glist = new ArrayList();
				elist = level();
				blobList = blobLevel();
				blobCounter = 0;
				flist = furyLevel();
				furyCounter = 0;
			}
			if (LU.leveled){
				LU.create(g, level);
				if (LU.size >= 200){
					LU = new LevelUp();
				}
			}
			if (player.health <= 0){
				end = true;
				endGame(g);
				g.dispose();
			}
			// Player
			g.setColor(Color.BLACK);
			g.fillOval(player.xCor, player.yCor, player.size, player.size);
			// BodyArmor
			if (bArmor.collected){
				bArmor.createArmor(g, player.xCor, player.yCor, player.size);
				player.damageE = 25;
				player.damageG = 50;
			}
			// Aimer
			crosshair.draw(g);
			// Level
			g.setColor(new Color(55, 179, 252));
			g.setFont(new Font("ariel", Font.BOLD, 20));
			g.drawString("LEVEL: " + level, 10, 30);
			// Kills
			g.setColor(new Color(255, 0, 51));
			g.setFont(new Font("ariel", Font.BOLD, 20));
			g.drawString("KILLS: " + kills, 10, 60);
			// Health
			g.setColor(new Color(39, 215, 69));
			g.setFont(new Font("ariel", Font.BOLD, 20));
			g.drawString("HEALTH: " + player.health, 10, 90);
			if (player.grenadeCount > 0){
				GPU.draw(g);
			}
			if (player.maxHealth > 100){
				g.setColor(new Color(34,139,34));
				g.fillRect(47, SCREENY - 23, 15, 5);
				g.fillRect(52, SCREENY - 28, 5, 15);
			}
		}
	}
	
	public ArrayList<Enemy> level(){
		ArrayList<Enemy> elist = new ArrayList();
		int counter = 0;
		while (counter <level){
			Enemy enemy = new Enemy(player.xCor, player.yCor);
			elist.add(enemy);
			counter += 1;
		}
		return elist;
	}
	
	public ArrayList<Blob> blobLevel(){
		ArrayList<Blob> blobList = new ArrayList();
		if (level >= 12 && level % 2 == 0){
			int counter = 0;
			while (counter < blobAmount){
				Blob blob = new Blob(player.xCor, player.yCor);
				blobList.add(blob);
				counter += 1;
			}
		}
		return blobList;
	}
	
	public ArrayList<Fury> furyLevel(){
		ArrayList<Fury> furyList = new ArrayList();
		if (level > 20 && level % 3 == 0){
			int counter = 0;
			while (counter < furyAmount){
				Fury fury = new Fury(player.xCor, player.yCor);
				furyList.add(fury);
				counter += 1;
			}
		}
		return furyList;
	}
	
	public void newGame(Graphics g){
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, SCREENX, SCREENY);
		g.setColor(new Color(189, 54, 255));
		g.setFont(new Font("ariel", Font.BOLD, 100));
		g.drawString("INFECTION", 100, 150);
		g.setColor(Color.BLACK);
		g.setFont(new Font("ariel", Font.BOLD, 20));
		g.drawString("PRESS ENTER TO CONTINUE", SCREENX - 400, SCREENY - 150);
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("ariel", Font.BOLD, 35));
		g.drawString("Created By Anmol Nagpal", 100, 300);
		mainScreenBlood.createMain(g);
		
	}
	
	public void endGame(Graphics g){
		g.setColor(new Color(55, 179, 252));
		g.setFont(new Font("ariel", Font.BOLD, 60));
		g.drawString("LEVEL: " + level, 200, 250);
		g.setColor(new Color(255, 0, 51));
		g.setFont(new Font("ariel", Font.BOLD, 60));
		g.drawString("KILLS: " + kills, 200, 450);
		g.setColor(new Color(255, 255, 0));
		g.drawString("SCORE: " + Math.round((((float) bulletKills)/((float) bullets))*100) * kills, 200, 650);
	}
	
	public void reset(){
		player = new Player();
		crosshair = new Crosshair();
		level = 1;
		dead = 0;
		kills = 0;
		bulletKills = 0;
		bullets = 0;
		bulletsAll = 0;
		begin = false;
		end = false;
		restart = false;
		elist = level();
		blist = new ArrayList();
		bloodList = new ArrayList();
		bArmor = new BodyArmor();
		glist = new ArrayList();
		GPU = new GrenadePickUp();
		healthPickUp = new Health();
		blobList = blobLevel();
		blobCounter = 0;
		flist = furyLevel();
		furyCounter = 0;
		blobAmount = 1;
		furyAmount = 3;
		LU = new LevelUp();
		levelUpStart = true;
		mainScreenBlood = new Blood(SCREENX/2, SCREENY/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		player.xCor += player.xSpeed;
		player.yCor += player.ySpeed;
		repaint();
	}
	
	public void up(){
		player.ySpeed = player.maxSpeed*-1;
	}
	
	public void down(){
		player.ySpeed = player.maxSpeed;
	}
	
	public void left(){
		player.xSpeed = player.maxSpeed*-1;
	}
	
	public void right(){
		player.xSpeed = player.maxSpeed;
	}
        
        //player movement
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
			up();
		}
		if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
			down();
		}
		if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
			left();
		}
		if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
			right();
		}
		if (code == KeyEvent.VK_ENTER && begin == false){
			begin = true;
		}
		
		if (code == KeyEvent.VK_ENTER && end == true){
			restart = true;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		crosshair.xCor = e.getX() - 23;
		crosshair.yCor = e.getY() - 46;	
		mousePressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
			player.ySpeed = 0;
		}
		if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
			player.ySpeed = 0;
		}
		if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
			player.xSpeed = 0;
		}
		if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){ 
			player.xSpeed = 0;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && begin == true){
			Bullet b = new Bullet(player.xCor + 10, player.yCor + 10, crosshair.xCor + crosshair.cushion, crosshair.yCor + crosshair.cushion, false);
			crosshair.animate();
			blist.add(b);
			}
		if (e.getButton() == MouseEvent.BUTTON3 && player.grenadeCount > 0){
			Grenade g = new Grenade(player.xCor + 10, player.yCor + 10, crosshair.xCor + crosshair.cushion, crosshair.yCor + crosshair.cushion, false);
			glist.add(g);
			player.grenadeCount -= 1;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		crosshair.xCor = e.getX() - 23;
		crosshair.yCor = e.getY() - 46;	
		mousePressed(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
        
	@Override
	public void keyTyped(KeyEvent e) {}


}