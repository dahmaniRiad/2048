package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.BLUE;

public class Controller implements Initializable {

    @FXML GridPane matrise ;
    @FXML Label r0c0;
    @FXML Label r0c1;
    @FXML Label r0c2;
    @FXML Label r0c3;
    @FXML Label r1c0;
    @FXML Label r1c1;
    @FXML Label r1c2;
    @FXML Label r1c3;
    @FXML Label r2c0;
    @FXML Label r2c1;
    @FXML Label r2c2;
    @FXML Label r2c3;
    @FXML Label r3c0;
    @FXML Label r3c1;
    @FXML Label r3c2;
    @FXML Label r3c3;
    @FXML Label SCOREVALUE;
    private boolean lose=true;
    private boolean win=true;
    static int score;
    int[][] tab=new int[4][4];
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lose=true;
        win=true;
        remplissageInitiale();
        remplissageDeGrid();
        upDateScore();
    }
    void remplissageInitiale(){
        int tmp,tmp1;

        tmp = (int) (0+(Math.random()*(4 - 0)));
        tmp1= (int) (0+(Math.random()*(4 - 0)));
        tab[tmp][tmp1]=2;
        tmp = (int) (0+(Math.random()*(4 - 0)));
        tmp1= (int) (0+(Math.random()*(4 - 0)));
        tab[tmp][tmp1]=2;
    }
    public void restClicked(){
        tab=new int[4][4];
        score=0;
        upDateScore();
        remplissageInitiale();
        remplissageDeGrid();
    }
    public void handleEnterPressed(KeyEvent keyEvent) throws SQLException, IOException {
        if(keyEvent.getCode()==KeyCode.UP){
            scrollUp();
        }
        else
            if(keyEvent.getCode()==KeyCode.DOWN){
                scrollDown();
            }
            else
                if(keyEvent.getCode()==KeyCode.RIGHT){
                    scrollRight();
                }
                else
                    if(keyEvent.getCode()==KeyCode.LEFT){
                        scrollLeft();
                    }
    }



    void scrollUp(){
        remplissageUp();
        for(int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                if(tab[i][j]==tab[i+1][j]||tab[i][j]==0){
                    lose=false;
                    if(i==0){
                        if(tab[2][j]==tab[3][j]){
                        tab[2][j]+=tab[3][j];
                        tab[3][j]=0;
                        score+=tab[2][j];
                        }
                    }
                    tab[i][j]+=tab[i+1][j];
                    tab[i+1][j]=0;
                    score+=tab[i][j];
                }
            }
        }
        if(!lose){
            ajouteUp();
            remplissageDeGrid();
            upDateScore();
        }
        else{
            System.out.println("You lose try again");
        }

    }

    void scrollDown(){
        remplissageDown();
        for(int i=3;i>0;i--){
            for (int j=0;j<4;j++){
                if(tab[i][j]==tab[i-1][j]||tab[i][j]==0){
                    lose=false;
                    if(i==3){
                        if(tab[1][j]==tab[0][j]){
                            tab[1][j]+=tab[0][j];
                            tab[0][j]=0;
                            score+=tab[1][j];
                        }
                    }
                    tab[i][j]+=tab[i-1][j];
                    tab[i-1][j]=0;
                    score+=tab[i][j];
                }
            }
        }
        if(!lose){
            ajouteDown();
            remplissageDeGrid();
            upDateScore();
        }
        else{
            System.out.println("You lose try again");
        }

    }

    void scrollRight(){
        remplissageRight();
            for(int j=3;j>0;j--){
                for(int i=0;i<4;i++)
                    if(tab[i][j]==tab[i][j-1]||tab[i][j]==0){
                        lose=false;
                        if(j==3){
                            if(tab[i][1]==tab[i][0]){
                                tab[i][1]+=tab[i][0];
                                tab[i][0]=0;
                                score+=tab[i][1];
                            }
                        }
                        tab[i][j]+=tab[i][j-1];
                        tab[i][j-1]=0;
                        score+=tab[i][j];
                    }
            }
        if(!lose){
            ajouteRight();
            remplissageDeGrid();
            upDateScore();
        }
        else{
            System.out.println("You lose try again");
        }

    }


    void scrollLeft(){
        remplissageLeft();
        for(int j=0;j<3;j++){
            for(int i=0;i<4;i++)
                if(tab[i][j]==tab[i][j+1]||tab[i][j]==0){
                    lose=false;
                    if(j==0){
                        if(tab[i][2]==tab[i][3]){
                            tab[i][2]+=tab[i][3];
                            tab[i][3]=0;
                            score+=tab[i][2];
                        }
                    }
                    tab[i][j]+=tab[i][j+1];
                    tab[i][j+1]=0;
                    score+=tab[i][j];
                }
        }
        if(!lose){
        ajouteLeft();
        remplissageDeGrid();
        upDateScore();
        }
        else{
            System.out.println("You lose try again");
        }
    }

void win(){
        if(win){
            System.out.println("You win ;)");
        }
}
    void remplissageUp(){
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                if(tab[i][j]==0){
                    lose=false;
                    int tmp=i+1;
                    while(tab[tmp][j]==0&&tmp<3)tmp++;
                    if(tab[tmp][j]!=0){
                        tab[i][j]=tab[tmp][j];
                        tab[tmp][j]=0;
                    }
                }
            }

        }
    }
    void remplissageDown(){
        for(int i=3;i>0;i--){
            for(int j=0;j<4;j++){
                if(tab[i][j]==0){
                    lose=false;
                    int tmp=i-1;
                    while(tab[tmp][j]==0&&tmp>0)tmp--;
                    if(tab[tmp][j]!=0){
                        tab[i][j]=tab[tmp][j];
                        tab[tmp][j]=0;
                    }
                }
            }
        }
    }
    void remplissageRight(){
        for(int j=3;j>0;j--){
            for(int i=0;i<4;i++){
                if(tab[i][j]==0){
                    lose=false;
                    int tmp=j-1;
                    while(tab[i][tmp]==0&&tmp>0)tmp--;
                    if(tab[i][tmp]!=0){
                        tab[i][j]=tab[i][tmp];
                        tab[i][tmp]=0;
                    }
                }
            }
        }
    }
    void remplissageLeft(){
        for(int j=0;j<3;j++){
            for(int i=0;i<4;i++){
                if(tab[i][j]==0){
                    lose=false;
                    int tmp=j+1;
                    while(tab[i][tmp]==0&&tmp<3)tmp++;
                    if(tab[i][tmp]!=0){
                        tab[i][j]=tab[i][tmp];
                        tab[i][tmp]=0;
                    }
                }
            }

        }
    }
    void ajouteUp(){
        ArrayList<Integer> num=new ArrayList<Integer>();
        for(int i=0;i<4;i++){
            if(tab[3][i]!=0)num.add(i);
        }
        if(num.size()!=4){
            int tmp=((int) (2+(Math.random()*(4 - 2))));
            int tmp1=(int) (0+(Math.random()*(3 - 0)));
            if(num.size()==3){
                int s=0;
                for(int i=0;i<num.size();i++){
                    s+=num.get(i);
                }
                tmp1=6-s;
            }
            else{
                while(num.contains(tmp1))tmp1=(int) (0+(Math.random()*(3 - 0)));
            }
            tab[3][tmp1]=(tmp==3)?4:tmp;
        }
    }

    void ajouteDown(){
        ArrayList<Integer> num=new ArrayList<Integer>();
        for(int i=0;i<4;i++){
            if(tab[0][i]!=0)num.add(i);
        }
        if(num.size()!=4){
            int tmp=((int) (2+(Math.random()*(4 - 2))));
            int tmp1=(int) (0+(Math.random()*(3 - 0)));
            if(num.size()==3){
                int s=0;
                for(int i=0;i<num.size();i++){
                    s+=num.get(i);
                }
                tmp1=6-s;
            }
            else{
                while(num.contains(tmp1))tmp1=(int) (0+(Math.random()*(3 - 0)));
            }
            tab[0][tmp1]=(tmp==3)?4:tmp;
        }
    }

    void ajouteLeft(){
        ArrayList<Integer> num=new ArrayList<Integer>();
        for(int i=0;i<4;i++){
            if(tab[i][3]!=0)num.add(i);
        }
        if(num.size()!=4){
            int tmp=((int) (2+(Math.random()*(4 - 2))));
            int tmp1=(int) (0+(Math.random()*(3 - 0)));
            if(num.size()==3){
                int s=0;
                for(int i=0;i<num.size();i++){
                    s+=num.get(i);
                }
                tmp1=6-s;
            }
            else{
            while(num.contains(tmp1))tmp1=(int) (0+(Math.random()*(3 - 0)));

            }
            tab[tmp1][3]=(tmp==3)?4:tmp;
        }
    }

    void ajouteRight(){
        ArrayList<Integer> num=new ArrayList<Integer>();
        for(int i=0;i<4;i++){
            if(tab[i][0]!=0) num.add(i);

        }
        if(num.size()!=4){
            int tmp=((int) (2+(Math.random()*(4 - 2))));
            int tmp1=(int) (0+(Math.random()*(3 - 0)));
            if(num.size()==3){
                int s=0;
                for(int i=0;i<num.size();i++){
                    s+=num.get(i);
                }
                tmp1=6-s;
            }
            else{
            while(num.contains(tmp1))tmp1=(int) (0+(Math.random()*(3 - 0)));

            }
            tab[tmp1][0]=(tmp==3)?4:tmp;
        }
    }

    void upDateScore(){
        SCOREVALUE.setText(String.valueOf(score));
    }
    void remplissageDeGrid(){
        r0c0.setText((tab[0][0]==0)?"":String.valueOf(tab[0][0]));changeCouleur(r0c0,tab[0][0]);
        r0c1.setText((tab[0][1]==0)?"":String.valueOf(tab[0][1]));changeCouleur(r0c1,tab[0][1]);
        r0c2.setText((tab[0][2]==0)?"":String.valueOf(tab[0][2]));changeCouleur(r0c2,tab[0][2]);
        r0c3.setText((tab[0][3]==0)?"":String.valueOf(tab[0][3]));changeCouleur(r0c3,tab[0][3]);
        r1c0.setText((tab[1][0]==0)?"":String.valueOf(tab[1][0]));changeCouleur(r1c0,tab[1][0]);
        r1c1.setText((tab[1][1]==0)?"":String.valueOf(tab[1][1]));changeCouleur(r1c1,tab[1][1]);
        r1c2.setText((tab[1][2]==0)?"":String.valueOf(tab[1][2]));changeCouleur(r1c2,tab[1][2]);
        r1c3.setText((tab[1][3]==0)?"":String.valueOf(tab[1][3]));changeCouleur(r1c3,tab[1][3]);
        r2c0.setText((tab[2][0]==0)?"":String.valueOf(tab[2][0]));changeCouleur(r2c0,tab[2][0]);
        r2c1.setText((tab[2][1]==0)?"":String.valueOf(tab[2][1]));changeCouleur(r2c1,tab[2][1]);
        r2c2.setText((tab[2][2]==0)?"":String.valueOf(tab[2][2]));changeCouleur(r2c2,tab[2][2]);
        r2c3.setText((tab[2][3]==0)?"":String.valueOf(tab[2][3]));changeCouleur(r2c3,tab[2][3]);
        r3c0.setText((tab[3][0]==0)?"":String.valueOf(tab[3][0]));changeCouleur(r3c0,tab[3][0]);
        r3c1.setText((tab[3][1]==0)?"":String.valueOf(tab[3][1]));changeCouleur(r3c1,tab[3][1]);
        r3c2.setText((tab[3][2]==0)?"":String.valueOf(tab[3][2]));changeCouleur(r3c2,tab[3][2]);
        r3c3.setText((tab[3][3]==0)?"":String.valueOf(tab[3][3]));changeCouleur(r3c3,tab[3][3]);
    }

    void changeCouleur(Label label,int val){
        switch (val){

            case 2:label.setStyle("-fx-background-color: #faffc5");break;
            case 4:label.setStyle("-fx-background-color: #d0c786");break;
            case 8:label.setStyle("-fx-background-color: #f7913e; -fx-text-fill: #FFF;");break;
            case 16:label.setStyle("-fx-background-color: #e5790e; -fx-text-fill: #FFF;");;break;
            case 32:label.setStyle("-fx-background-color: #ff503a; -fx-text-fill: #FFF;");break;
            case 64:label.setStyle("-fx-background-color: #ff2c18; -fx-text-fill: #FFF;");break;
            case 128:label.setStyle("-fx-background-color: #ffcb4c; -fx-text-fill: #FFF;");break;
            case 256:label.setStyle("-fx-background-color: #ffdb3f; -fx-text-fill: #FFF;");break;
            case 512:label.setStyle("-fx-background-color: #ffde25; -fx-text-fill: #FFF;");break;
            case 1024:label.setStyle("-fx-background-color: #ffea12; -fx-text-fill: #FFF;");break;
            case 2048:label.setStyle("-fx-background-color: #fffb00; -fx-text-fill: #FFF;");break;
            default:label.setStyle("-fx-background-color: #b3ad91");break;

        }

    }
}
