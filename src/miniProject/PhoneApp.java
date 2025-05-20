package miniProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {
	
	public static void main(String[] args) throws IOException {
		
		//키보드 연결
		Scanner sc = new Scanner(System.in);
		final String FILE_PATH = "C:\\javaStudy\\workspace\\MiniProject\\PhoneDB.txt";
		
		while(true) {
			System.out.println("**************************************");
			System.out.println("*       전화번호 관리 프로그램       *");
			System.out.println("**************************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println(">메뉴번호: ");
			
			int menuNum = sc.nextInt();
			sc.nextLine(); //버퍼 비우기
			
			System.out.println("입력한 값은 "+menuNum+"입니다.");
			
			
			
			if(menuNum==1) {
				System.out.println("<1.리스트>");
				
				//읽기 스트림 준비
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), "MS949"));
				
				String line;
				int index = 1;
				
				while ((line = br.readLine()) != null) {
					String[] info = line.split(",");
                    String name = info[0];
					String hp = info[1];
					String company = info.length >= 3 ? info[2] : "-";
					AddressBook person = new AddressBook(name, hp, company);
					System.out.println(index + ". " + person.toString());
					
                    index++;
				}
				
				br.close();
                System.out.println();
				
			}else if(menuNum==2) {
				System.out.println("<2.등록>");
				
				System.out.print("이름: ");
                String name = sc.nextLine();
                System.out.print("핸드폰: ");
                String hp = sc.nextLine();
                System.out.print("회사: ");
                String company = sc.nextLine();
                
                //쓰기 스트림 준비
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH, true), "MS949"));
                
                bw.write(name + "," + hp + "," + company);
                bw.newLine();
                bw.close();
                System.out.println("등록되었습니다.\n");
				
			}else if(menuNum==3) {
				System.out.println("<3.삭제>");
				
				//읽기 스트림 준비
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), "MS949"));
				
				List<String> lines = new ArrayList<>();
                
				String line;
                int index = 1;
                
                while ((line = br.readLine()) != null) {
                	String[] info = line.split(",");
                    String name = info[0];
					String hp = info[1];
					String company = info.length >= 3 ? info[2] : "-";
					AddressBook person = new AddressBook(name, hp, company);
					System.out.println(index + ". " + person.toString());
                    lines.add(line);
                    index++;
                }
                
                br.close();
                
                System.out.print("삭제할 번호: ");
                int delIndex = sc.nextInt();
                sc.nextLine(); // 줄바꿈 문자 제거

                if (delIndex >= 1 && delIndex <= lines.size()) {
                    lines.remove(delIndex - 1);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), "MS949"));
                    for (String l : lines) {
                        bw.write(l);
                        bw.newLine();
                    }
                    bw.close();
                    System.out.println("삭제되었습니다.\n");
                } else {
                    System.out.println("유효하지 않은 번호입니다.\n");
                }
                
			}else if(menuNum==4) {
				System.out.println("<4.검색>");
				
				System.out.print("검색할 이름: ");
                String keyword = sc.nextLine();

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), "MS949"));
                String line;
                boolean found = false;
                int index = 1;
                
                while ((line = br.readLine()) != null) {
                    if (line.contains(keyword)) {
                    	String[] info = line.split(",");
                        String name = info[0];
    					String hp = info[1];
    					String company = info.length >= 3 ? info[2] : "-";
    					AddressBook person = new AddressBook(name, hp, company);
    					System.out.println(index + ". " + person.toString());
                        found = true;
                        index++;
                    }
                }
                br.close();

                if (!found) {
                    System.out.println("일치하는 연락처가 없습니다.");
                }
                System.out.println();
				
			}else if(menuNum==5) {
				System.out.println("<5.종료>");
				
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				System.out.println("다시 입력해주세요.\n");
			}
			
		}
		
		//키보드 해제
		sc.close();
		
	}
	
}