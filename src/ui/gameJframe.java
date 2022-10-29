package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectStreamException;
import java.util.Random;

public class gameJframe extends JFrame implements KeyListener, ActionListener {
 String path = "image\\girl\\girl1\\";

 int stepp = 0;
 int i ;
 int[][] win = new int[][]{
         {1,2,3,4},
         {5,6,7,8},
         {9,10,11,12},
         {13,14,15,0}
 };

 JMenuItem replayItem = new JMenuItem("重新开始");
 JMenuItem relogItem = new JMenuItem("重新登陆");
 JMenuItem closeItem = new JMenuItem("关闭游戏");
 JMenuItem aboutItem = new JMenuItem("二维码");
 JMenu changeItem = new JMenu("切换图片");
 JMenuItem animal = new JMenuItem("动物");
 JMenuItem prettygril = new JMenuItem("美女");

 int[][] data = new int[4][4];//创建二维数组,保存图片位置
 int x=0;
 int y=0;
 public gameJframe(){
 //设置窗口
  initJframe();

  //设置菜单
  initJmenubar();

  //打乱数据
  initData();

  //设置图片
  initicon();


  this.setVisible(true);//设置窗口显示

 }

 private void initData() {
  int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};//随机打乱1-15
  Random r = new Random();
  for (int i = 0; i < tempArr.length; i++) {
   int index = r.nextInt(tempArr.length);
   int temp = tempArr[i];
   tempArr[i] = tempArr[index];
   tempArr[index] = temp;
  }


  //解法一:
  for (int i = 0; i < tempArr.length; i++) {
   if (tempArr[i] == 0) {
    x = i / 4;
    y = i % 4;
   }
    data[i / 4][i % 4] = tempArr[i];

  }
 }


 private void initicon() {
//移出所有图片
  this.getContentPane().removeAll();

  //添加step
JLabel step = new JLabel("step:"+stepp+"步");
step.setBounds(50,30,50,30);
this.getContentPane().add(step);
  //添加成功图片
  JLabel victory = new JLabel(new ImageIcon("image\\win.png"));
  victory.setBounds(200,200,197,73);
if(victory()){
  this.getContentPane().add(victory);}

  for (int i = 0; i < 4; i++) {
   for (int j = 0; j < 4; j++) {
    //获取图片序号
    int num = data[i][j];
    ImageIcon icon = new ImageIcon(path+num+".jpg");//创建图像对象
    JLabel jLabel = new JLabel(icon);//创建管理容器
    jLabel.setBounds(105*j+83,105*i+134,105,105);
    //this.add(jLabel1);
    //给图片添加边框
    jLabel.setBorder(new BevelBorder(1));//0:让图片凸起 1:让图片凹陷 BeverBorder:斜面边框
    this.getContentPane().add(jLabel);
   }
  }
  //添加背景图片
  JLabel background = new JLabel(new ImageIcon("image\\background.png"));
  background.setBounds(40,40,508,560);
 this.getContentPane().add(background);

 //加载所有图片
  this.getContentPane().repaint();


 }

  private void initJmenubar() {
  JMenuBar jmenubar = new JMenuBar();//菜单栏
  JMenu functionJMenu = new JMenu("功能");//菜单
  JMenu aboutJMenu = new JMenu("关于我们");//菜单

  //给按键绑定事件
  replayItem.addActionListener(this);
  relogItem.addActionListener(this);
  closeItem.addActionListener(this);
  aboutItem.addActionListener(this);
  animal.addActionListener(this);
  prettygril.addActionListener(this);
  //互相绑定
   changeItem.add(animal);
   changeItem.add(prettygril);
   functionJMenu.add(changeItem);
  functionJMenu.add(replayItem);
  functionJMenu.add(relogItem);
  functionJMenu.add(closeItem);


  aboutJMenu.add(aboutItem);
  jmenubar.add(functionJMenu);
  jmenubar.add(aboutJMenu);
  this.setJMenuBar(jmenubar) ;

 }

 private void initJframe() {
  this.setSize(603,680);//设置窗口尺寸
  this.setTitle("拼图小游戏");//设置窗口名称
  this.setAlwaysOnTop(true);//设置置顶
  this.setLocationRelativeTo(null);//设置界面居中
  this.setDefaultCloseOperation(3);//设置关闭模式
  this.setLayout(null);
  this.addKeyListener(this);

 }

 @Override
 public void keyTyped(KeyEvent e) {

 }

 @Override
 public void keyPressed(KeyEvent e) {
int code=e.getKeyCode();
if (code==65){
 this.getContentPane().removeAll();
 JLabel allimage = new JLabel(new ImageIcon(path+"all.jpg"));
 allimage.setBounds(83,134,420,420);
 this.getContentPane().add(allimage);
 //添加背景图片
 JLabel background = new JLabel(new ImageIcon("image\\background.png"));
 background.setBounds(40,40,508,560);
 this.getContentPane().add(background);
 this.getContentPane().repaint();
}
 }


 @Override
 public void keyReleased(KeyEvent e) {

  if(victory()){
   return ;
  }
  //左:37 上:38 右:39 下:40
int code = e.getKeyCode();
if(code == 37){
 stepp++;
 System.out.println("向左");
 if(y==3){
  return ;
 }
 data[x][y]=data[x][y+1];
 data[x][y+1]=0;
 y++;
 initicon();
}else if(code ==38){
 stepp++;
 System.out.println("向上");
 if(x==3){
  return ;
 }
 //把空白下方的坐标传递给空白
 data[x][y]=data[x+1][y];
 data[x+1][y]=0;
 x++;
 initicon();
}else if(code ==39){
 stepp++;
 System.out.println("向右");
 if(y==0){
  return ;
 }
 data[x][y]=data[x][y-1];
 data[x][y-1]=0;
 y--;
 initicon();
}else if(code ==40){
 stepp++;
 if(x==0){
  return ;
 }
 System.out.println("向下");
 data[x][y]=data[x-1][y];
 data[x-1][y]=0;
 x--;
 initicon();
} else if (code == 65) {
 initicon();}
 else if(code == 87){
  data = new int[][]{
  {1,2,3,4},
  {5,6,7,8},
  {9,10,11,12},
  {13,14,15,0}
 };
  initicon();
}

 }

 //判断是否胜利
 public boolean victory(){
  for (int i = 0; i < data.length; i++) {
   for (int i1 = 0; i1 < data[i].length; i1++) {
    if (data[i][i1]!=win[i][i1])
     return false;
   }
  }
  return true;

 }


 @Override
 public void actionPerformed(ActionEvent e) {
  Object obj = e.getSource();
 if (obj == closeItem){
  System.out.println("退出游戏");
  System.exit(0);
 }
 if(obj == replayItem){
  System.out.println("重新游戏");
stepp = 0;
  initData();
initicon();
 }
 if(obj == relogItem){
  System.out.println("重新登陆");
 }
 if(obj == aboutItem) {
  JDialog about = new JDialog();
  JLabel aboutjlabe = new JLabel(new ImageIcon("image\\about.png"));
  aboutjlabe.setBounds(0, 0, 677, 671);
  about.getContentPane().add(aboutjlabe);
  about.setSize(750, 750);
  about.setAlwaysOnTop(true);
  about.setLocationRelativeTo(null);
  about.setModal(true);
  about.setVisible(true);
 }
  if(obj == animal){
   Random r = new Random();
   int a = r.nextInt(8);
   if(i==a){i++;}else{i=a;}
   path = "image\\animal\\animal"+i+"\\";
   System.out.println(path);
   stepp = 0;
   initData();
   initicon();
  }
  if(obj == prettygril){
   Random r = new Random();
   int a = r.nextInt(14);
   if(i==a){i++;}else{i=a;}

   path = "image\\girl\\girl"+i+"\\";
   System.out.println(path);
   stepp = 0;
   initData();
   initicon();
  }
 }

  }


