class main{
	public static void main(String[] args){
		BallDemo ball = new BallDemo();
		ball.setCanvas(800, 400);
		ball.drawFrame();
		ball.bounce(5);
	}
}
