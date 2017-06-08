package in.koyad.piston.common.constants;

public enum MsgType {
	INFO, WARNING, ERROR, SUCCESS;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
	public static void main(String[] args) {
		System.out.println(MsgType.INFO);
	}
}
