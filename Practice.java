package practice;
import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PFont;
public class Practice extends PApplet{
    float scale = 0.5f;
    int screenSize = (int)(1000 * scale);
    float playerX = 0;
    float playerY = 0;
    boolean up, down, left, right;

    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    class Enemy {
        float x, y, vx, vy;
        boolean eup, edown, eleft, eright;
        char direction;
        Enemy(float x, float y, char direct) {
            this.x = x;
            this.y = y;
            this.edown = true;
            this.eup = false;
            this.eleft = false;
            this.eright = true;
            this.direction = direct;
        }
        public void drawEnemy() {
            fill(0, 230, 100);
            //rectMode(CENTER);
            x = constrain(x, x, x);
            y = constrain(y, 125 * scale, height - 125 * scale);
            imageMode(CENTER);
            image(enemyImg, x, y);
            //rect(x, y, 50, 50);
            //playerX = constrain(playerX, 0, width);
            //playerY = constrain(playerY, 0, height);
            //rect(playerX, playerY, 30, 30);
        }
        public void move() {
            if (direction == 'V') {
                // enemy moves in a vertical straight line
                if (eup) {
                    if (y > 125 * scale) {
                        y -= 2.5 * scale;
                    } else {
                        // has reached top, switch to move down
                        eup = false;
                        edown = true;
                    }
                }
                if (edown) {
                    if (y < 875 * scale) {
                        y += 2.5 * scale;
                    } else {
                        // has reached bottom, switch to move up
                        eup = true;
                        edown = false;
                    }
                }
            } else {
                // enemy moves in a horizontal straight line
                if (eright) {
                    if (x > 125 * scale) {
                        x -= 2.5 * scale;
                    } else {
                        // has reached top, switch to move down
                        eright = false;
                        eleft = true;
                    }
                }
                if (eleft) {
                    if (x < 875 * scale) {
                        x += 2.5 * scale;
                    } else {
                        // has reached bottom, switch to move up
                        eright = true;
                        eleft = false;
                    }
                }
            }

        }
    }

    ArrayList<Token> tokens = new ArrayList<Token>();
    class Token {
        float x, y, tx, ty;
        int tokenType = 0;
        int value;
        boolean found = false;
        Token(float x, float y, int tokenType) {
            this.x = x;
            this.y = y;
            this.tokenType = tokenType;
        }
        public void drawTokens() {
            if (found == false) {
                // token 0: dark green
                if (tokenType == 0) {
                    imageMode(CENTER);
                    image(tokenImg1, x, y);
                    this.value = 10;
                }
                // token 1: purple
                if (tokenType == 1) {
                    imageMode(CENTER);
                    image(tokenImg2, x, y);
                    this.value = 20;
                }
                if (tokenType == 2) {
                    imageMode(CENTER);
                    image(tokenImg3, x, y);
                    this.value = 30;
                }

            }
        }
    }

    ArrayList<Island> islands = new ArrayList<Island>();
    class Island {
        float xStart, yStart, iwidth, iheight;
        String hedgeType;
        Island(float x, float y, float iw, float ih, String type) {
            this.xStart = x;
            this.yStart = y;
            this.iwidth = iw;
            this.iheight = ih;
            this.hedgeType = type;
        }
        public void drawIsland() {
            switch(hedgeType) {
                case "1V":
                    imageMode(CORNER);
                    hedgeImg1V.resize((int)(800 * scale), (int)(800 * scale));
                    image(hedgeImg1V, xStart, yStart);
                    break;
                case "2V":
                    imageMode(CORNER);
                    hedgeImg2V.resize((int)(800 * scale), (int)(800 * scale));
                    image(hedgeImg2V, xStart, yStart);
                    break;
                case "3V":
                    imageMode(CORNER);
                    hedgeImg3V.resize((int)(800 * scale), (int)(800 * scale));
                    image(hedgeImg3V, xStart, yStart);
                    break;
                case "2H":
                    imageMode(CORNER);
                    hedgeImg2H.resize((int)(800 * scale), (int)(800 * scale));
                    image(hedgeImg2H, xStart, yStart);
                    break;
                case "3H":
                    imageMode(CORNER);
                    hedgeImg3H.resize((int)(800 * scale), (int)(800 * scale));
                    image(hedgeImg3H, xStart, yStart);
                    break;
            }
        }
    }

    PImage backgroundImg1;
    PImage backgroundImg2;
    //PImage backgroundImg3;
    PImage backgroundTitle;
    PImage playerImg;
    PImage enemyImg;
    PImage tokenImg1;
    PImage tokenImg2;
    PImage tokenImg3;
    PImage tokenImg4;
    PImage tokenImg5;
    PImage hedgeImg1V;
    PImage hedgeImg2V;
    PImage hedgeImg3V;
    PImage hedgeImg2H;
    PImage hedgeImg3H;


    int score = 0;
    int highScore = 0;
    PFont scoreFont;
    PFont titleFont;

    enum GameState {
        START,
        OVER,
        WIN,
        PAGE1,
        PAGE2,
        PAGE3,
        PAGE4
    }

    static GameState currentState;

    public static void main(String[] args) {
        PApplet.main("practice.Practice");
    }
    public void settings() {
        size(screenSize, screenSize);
    }
    public void setup() {
        String[] fontList = PFont.list();

        currentState = GameState.START;
        backgroundImg1 = loadImage("Images/BackgroundNewC.png");
        backgroundImg1.resize(screenSize, screenSize);
        backgroundImg2 = loadImage("Images/BackgroundNewD.png");
        backgroundImg2.resize(screenSize, screenSize);
        backgroundTitle = loadImage("Images/BackgroundSTARTOVER.png");
        backgroundTitle.resize(screenSize, screenSize);
        playerImg = loadImage("Images/Cat.png");
        playerImg.resize((int)(225 * scale), (int)(225 * scale));
        enemyImg = loadImage("Images/Dog1.png");
        enemyImg.resize((int)(200 * scale), (int)(200 * scale));
        tokenImg1 = loadImage("Images/Token1.png");
        tokenImg1.resize((int)(175 * scale), (int)(175 * scale));
        tokenImg2 = loadImage("Images/Token2.png");
        tokenImg2.resize((int)(175 * scale), (int)(175 * scale));
        tokenImg3 = loadImage("Images/Token3.png");
        tokenImg3.resize((int)(175 * scale), (int)(175 * scale));
        hedgeImg1V = loadImage("Images/Hedge1V.png");
        hedgeImg2V = loadImage("Images/Hedge2V.png");
        hedgeImg3V = loadImage("Images/Hedge3V.png");
        hedgeImg2H = loadImage("Images/Hedge2H.png");
        hedgeImg3H = loadImage("Images/Hedge3H.png");
    }
    public void draw() {
        switch (currentState) {
            case START:
                drawBackground();
                drawStart();
                break;
            case PAGE1:
                playGame();
                // switch rooms if finished level
                if (playerX < 130 * scale && playerY < 600 * scale && playerY > 400 * scale) {
                    currentState = GameState.PAGE2;
                    // reset playerX to be on left side again
                    playerX = 900 * scale;
                    prepareNextPage();
                }
                drawPlayer();
                break;

            case PAGE2:
                playGame();
                if (playerX < 130 * scale && playerY < 600 * scale && playerY > 400 * scale) {
                    currentState = GameState.PAGE3;
                    playerX = 900 * scale;
                    prepareNextPage();
                }
                drawPlayer();
                break;
            case PAGE3:
                playGame();
                if (playerX < 130 * scale && playerY < 600 * scale && playerY > 400 * scale) {
                    currentState = GameState.PAGE4;
                    playerX = 900 * scale;
                    prepareNextPage();
                }
                drawPlayer();
                break;

            case PAGE4:
                playGame();
                if (playerX < 130 * scale && playerY < 600 * scale && playerY > 400 * scale) {
                    currentState = GameState.WIN;
                    playerX = 900 * scale;
                    prepareNextPage();
                }
                drawPlayer();
                break;
            case WIN:
                drawGameWin();
                break;
            case OVER:
                drawGameOver();
                break;
        }
    }
    public void playGame() {
        drawBackground();
        drawScore();

        // draw all the islands
        for (int i = 0; i < islands.size(); i++) {
            Island is = islands.get(i);
            is.drawIsland();
            // check for collisions with island
            if ((playerY >= is.yStart && playerY <= is.yStart + is.iheight) && (playerX >= is.xStart && playerX <= is.xStart + is.iwidth)) {
                // hitting from top
                if (down) {
                    if (playerY <= is.yStart + 15 * scale) {
                        playerY = is.yStart - 15 * scale;
                    }
                }
                // hitting from bottom
                if (up) {
                    if (playerY >= is.yStart + is.iheight - 15 * scale) {
                        playerY = is.yStart + is.iheight + 15 * scale;
                    }
                }
                // hitting from left
                if (right) {
                    if (playerX <= is.xStart + 15 * scale) {
                        playerX = is.xStart - 15 * scale;
                    }
                }
                // hitting from right
                if (left) {
                    if (playerX >=  is.xStart + is.iwidth - 15 * scale) {
                        playerX = is.xStart + is.iwidth + 15 * scale;
                    }
                }
            }
        }
        // draw all the tokens
        for (int i = 0; i < tokens.size(); i++) {
            Token tk = tokens.get(i);
            tk.drawTokens();
            // check for collisions with token
            if (abs(playerX - tk.x) < 50 * scale && abs(playerY - tk.y) < 50 * scale) {
                // update high score as needed
                score += tk.value;
                if (score > highScore) {
                    highScore = score;
                }
                tokens.remove(i);
            }
        }
        // draw all the enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy en = enemies.get(i);
            en.drawEnemy();
            en.move();
            // check for collisions with enemy
            if (abs(playerX - en.x) < 50 * scale && abs(playerY - en.y) < 50 * scale) {
                // update high score as needed
                if (score > highScore) {
                    highScore = score;
                }
                currentState = GameState.OVER;
            }
        }
    }
    public void drawBackground() {
        background(250);
        imageMode(CORNER);

        switch (currentState) {
            case START:
                image(backgroundImg2, 0, 0);
                break;
            case PAGE1:
                image(backgroundImg1, 0, 0);
                break;

            case PAGE2:
                image(backgroundImg2, 0, 0);
                break;
            case PAGE3:
                image(backgroundImg1, 0, 0);
                break;

            case PAGE4:
                image(backgroundImg2, 0, 0);
                break;
        }
    }
    public void drawPlayer() {
        if (up) {
            playerY -= 5 * scale;
        }
        if (down) {
            playerY += 5 * scale;
        }
        if (left) {
            playerX -= 5 * scale;
        }
        if (right) {
            playerX += 5 * scale;
        }

        fill(0, 230, 172);
        imageMode(CENTER);
        image(playerImg, playerX, playerY);
        //rectMode(CENTER);

        // constrain y top and bottom
        playerY = constrain(playerY, 125 * scale, height - 125 * scale);
        // constrain x doors
        playerX = constrain(playerX, 125 * scale, width - 125 * scale);

        //rect(playerX, playerY, 30, 30);
    }
    public void drawScore() {
        scoreFont = createFont("Ravie", 26 * scale, true);
        //scoreFont = createFont("Leelawadee UI Bold", 26, true);
        textFont(scoreFont);
        fill(255, 255, 255);
        textAlign(CENTER);
        text("Score: " + score, width - 90 * scale, 40 * scale);
        text("High Score: " + highScore, 150 * scale, 40 * scale);
    }
    public void drawStart() {
        imageMode(CORNER);
        image(backgroundTitle, 0, 0);
        fill(122, 64, 51);
        textAlign(CENTER);

        titleFont = createFont("Snap ITC", 26 * scale, true);
        textFont(titleFont);
        textSize(100 * scale);
        text("FRUIT FIELD", width/2 + 15 * scale, height/2 - 275 * scale);
        textSize(25);
        scoreFont = createFont("Ravie", 26 * scale, true);
        textFont(scoreFont);

        text("Press Space Key", width/2 + 15 * scale, height/2 - 30 * scale);
        text("to Begin", width/2 + 15 * scale, height/2 + 0 * scale);
        text("Use WASD keys", width/2 + 15 * scale, height/2 + 60 * scale);
        text("to move", width/2 + 15 * scale, height/2 + 90 * scale);
        //text("High Score: " + highScore, width/2 + 50, height/2);
    }
    public void drawGameOver() {
        imageMode(CORNER);
        image(backgroundTitle, 0, 0);

        textFont(scoreFont);
        fill(122, 64, 51);
        textAlign(CENTER);
        titleFont = createFont("Snap ITC", 26 * scale, true);
        scoreFont = createFont("Ravie", 26 * scale, true);

        textFont(titleFont);
        textSize(100 * scale);
        text("GAME OVER", width/2 + 15 * scale, height/2 - 275 * scale);
        textSize(25 * scale);
        textFont(scoreFont);
        text("Score: " + score, width/2 + 15 * scale, height/2 - 60 * scale);
        text("High Score: " + highScore, width/2 + 15 * scale, height/2 - 30 * scale);
        text("Press Space Key", width/2 + 15 * scale, height/2 + 40 * scale);
        text("to Play Again ", width/2 + 15 * scale, height/2 + 70 * scale);
    }
    public void drawGameWin() {
        imageMode(CORNER);
        image(backgroundTitle, 0, 0);

        fill(122, 64, 51);
        textAlign(CENTER);
        titleFont = createFont("Snap ITC", 26 * scale, true);
        scoreFont = createFont("Ravie", 26 * scale, true);

        textFont(titleFont);
        textSize(100);
        text("YOU WIN!", width/2 + 15 * scale, height/2 - 275 * scale);
        textSize(25);
        textFont(scoreFont);
        text("Score: " + score, width/2 + 15 * scale, height/2 - 60 * scale);
        text("High Score: " + highScore, (width/2 + 15) * scale, height/2 - 30 * scale);
        text("Press Space Key", width/2 + 15 * scale, height/2 + 50 * scale);
        text("to Play Again ", width/2 + 15 * scale, height/2 + 80 * scale);
    }
    public void generateEnemy() {
        switch (currentState) {
            case PAGE1:
                enemies.add(new Enemy(600 * scale, 25 * scale, 'V'));
                break;

            case PAGE2:
                enemies.add(new Enemy(150 * scale, 25 * scale, 'V'));
                enemies.add(new Enemy(300 * scale, 800 * scale, 'H'));
                break;
            case PAGE3:
                enemies.add(new Enemy(150 * scale, 650 * scale, 'H'));
                enemies.add(new Enemy(600 * scale, 150 * scale, 'H'));
                break;


            case PAGE4:
                enemies.add(new Enemy(150 * scale, 25 * scale, 'V'));
                enemies.add(new Enemy(350 * scale, 500, 'V'));
                enemies.add(new Enemy(600 * scale, 900 * scale, 'V'));
                break;
        }
    }
    public void generateTokens() {
        switch (currentState) {
            case PAGE1:
                // map 1
                tokens.add(new Token(500 * scale, 200 * scale, 0));
                tokens.add(new Token(700 * scale, 700 * scale, 2));
                tokens.add(new Token(200 * scale, 500 * scale, 1));
                break;

            case PAGE2:
                tokens.add(new Token(700 * scale, 200 * scale, 2));
                tokens.add(new Token(150 * scale, 775 * scale, 0));
                tokens.add(new Token(600 * scale, 500 * scale, 1));
                break;
            case PAGE3:
                tokens.add(new Token(300 * scale, 175 * scale, 2));
                tokens.add(new Token(800 * scale, 200 * scale, 0));
                tokens.add(new Token(300 * scale, 675 * scale, 0));
                tokens.add(new Token(800 * scale, 875 * scale, 1));
                break;

            case PAGE4:
                tokens.add(new Token(150 * scale, 700 * scale, 1));
                tokens.add(new Token(190 * scale, 250 * scale, 2));
                tokens.add(new Token(350 * scale, 350 * scale, 0));
                tokens.add(new Token(590 * scale, 450 * scale, 1));
                break;
        }

    }
    public void generateIslands() {
        switch (currentState) {
            case PAGE1:
                islands.add(new Island(375 * scale, 275 * scale, 175 * scale, 450 * scale, "2V"));
                islands.add(new Island(150 * scale, 650 * scale, 175 * scale, 275 * scale, "1V"));
                islands.add(new Island(700 * scale, 150 * scale, 175 * scale, 275 * scale, "1V"));
                break;

            case PAGE2:
                islands.add(new Island(175 * scale, 250 * scale, 650 * scale, 175 * scale, "3H"));
                islands.add(new Island(175 * scale, 550 * scale, 650 * scale, 175 * scale, "3H"));
                break;
            case PAGE3:
                islands.add(new Island(200 * scale, 175 * scale, 175 * scale, 450 * scale, "2V"));
                islands.add(new Island(300 * scale, 175 * scale, 450 * scale, 175 * scale, "2H"));
                islands.add(new Island(450 * scale, 650 * scale, 450 * scale, 175 * scale, "2H"));
                break;

            case PAGE4:
                islands.add(new Island(175 * scale, 75 * scale, 175 * scale, 650 * scale, "3V"));
                islands.add(new Island(400 * scale, 300 * scale, 175 * scale, 650 * scale, "3V"));
                islands.add(new Island(625 * scale, 75 * scale, 175 * scale, 650 * scale, "3V"));
                break;
        }
    }
    public void keyPressed() {
        if (key == 'w') {
            up = true;
        }
        if (key == 's') {
            down = true;
        }
        if (key == 'a') {
            left = true;
        }
        if (key == 'd') {
            right = true;
        }
        if (currentState == GameState.START || currentState == GameState.OVER || currentState == GameState.WIN) {
            if (key == ' ') {
                currentState = GameState.PAGE1;
                prepareNextPage();
                playerX = 900 * scale;
                playerY = 500 * scale;
                score = 0;
            }
        }

    }
    public void keyReleased() {
        if (key == 'w') {
            up = false;
        }
        if (key == 's') {
            down = false;
        }
        if (key == 'a') {
            left = false;
        }
        if (key == 'd') {
            right = false;
        }
    }
    public void clearBoard() {
        // clear enemies
        for (int i = enemies.size() - 1; i >= 0; i--) {
            enemies.remove(i);
        }
        // clear tokens
        for (int i = tokens.size() - 1; i >= 0; i--) {
            tokens.remove(i);
        }
        // clear islands
        for (int i = islands.size() - 1; i >= 0; i--) {
            islands.remove(i);
        }
    }
    public void prepareNextPage() {
        clearBoard();
        generateEnemy();
        generateTokens();
        generateIslands();
    }
}
