public class Tank {

	int x, y;
	int dir;
	int fuel; // Кол-во топлива
	
	public Tank() {
	    this(0,0,100);
	}
	
	public Tank(int x, int  y) {
	    this(x,y,100);
	}
	
	public Tank(int x, int  y, int fuel) {
	    this.x = x;
	    this.y = y;
	    this.fuel = fuel; // Заправляемся!
	}

	public void goForward(int i) {
	    int can_drive = i; // Хотим проехать такое расстояние, ...
	    if (this.fuel < i) {
	        can_drive = this.fuel; // ... но, топлива только на столько!
	    }
	    
	    // Едем
		if (dir == 0) x += can_drive;
		else if (dir == 1) y += can_drive;
		else if (dir == 2) x -= can_drive;
		else y -= can_drive;
		
		// Расходуем топливо
		this.fuel -= can_drive;
	}
	
	public void printPosition() {
		System.out.println("The Tank is at " + x +", " + y + " now.");
	}

	public void turnLeft() {
		dir--;
        if (dir == -1) dir = 3;
	}

	public void turnRight() {
		dir++;
		if (dir == 4) dir = 0;
	}

	public void goBackward(int i) {
        goForward(-i);
	}
}