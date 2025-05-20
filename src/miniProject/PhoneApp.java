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
		
		//파일경로
		//final은 변경 불가능한 상수
		final String FILE_PATH = "C:\\javaStudy\\workspace\\MiniProject\\PhoneDB.txt";
		
		while(true) {
			System.out.println("**************************************");
			System.out.println("*       전화번호 관리 프로그램       *");
			System.out.println("**************************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println(">메뉴번호: ");
			
			//nextInt()로 숫자를 입력 받는다
			int menuNum = sc.nextInt();
			
			//nextLine()은 숫자 입력 후 남은 줄바꿈 문자를 제거한다
			//안 쓰면 다음 입력이 꼬인다
			sc.nextLine();
			
			System.out.println("입력한 값은 " + menuNum + "입니다");
			
			if(menuNum==1) {
				System.out.println("<1.리스트>");
				
				//읽기 스트림 준비(파일을 한 줄씩 읽기 위해 준비)
				//"MS949"는 한글 인코딩
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH),"MS949"));
				
				String line; //한 줄씩 읽을 때 사용하는 변수
				int index = 1; //번호출력용
				
				//한 줄씩 읽으면서, 파일 끝까지 반복
				while((line = br.readLine()) != null) {
					String[] info = line.split(","); //쉼표로 나눈 문자열을 배열로 저장
					String name = info[0];
					String hp = info[1];
					String company = info.length >= 3 ? info[2] : "-"; //회사 정보가 없으면 "-"로 처리
					
					//AddressBook 객체를 만들고 출력
					AddressBook person = new AddressBook(name, hp, company);
					System.out.println(index + ". " + person.toString());
					
					index++;
				}
				
				//읽기 스트림 종료(파일 닫기)
				br.close();
				System.out.println();
				
			}else if(menuNum==2) {
				System.out.println("<2.등록>");
				
				//사용자에게 이름, 핸드폰, 회사 정보를 입력 받는다
				System.out.println("이름: ");
				String name = sc.nextLine();
				System.out.println("핸드폰: ");
				String hp = sc.nextLine();
				System.out.println("회사: ");
				String company = sc.nextLine();
				
				//쓰기 스트림 준비(파일을 열고 이어쓰기(append) 모드로 준비)
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH, true), "MS949"));
				
				//입력받은 내용을 파일에 한 줄로 저장하고 닫는다
				bw.write(name + "," + hp + "," + company);
				bw.newLine();
				bw.close();
				System.out.println("등록되었습니다");
				
			}else if(menuNum==3) {
				System.out.println("<3.삭제>");
				
				//연락처 목록을 저장할 리스트를 생성한다
				List<String> lines = new ArrayList<>();
				
				//읽기 스트림 준비
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), "MS949"));
				
				String line;
				int index = 1;
				
				while((line = br.readLine()) != null) {
					String[] info = line.split(",");
					String name = info[0];
					String hp = info[1];
					String company = info.length >= 3 ? info[2] : "-";
					
					//AddressBook 객체를 만들고 출력
					AddressBook person = new AddressBook(name, hp, company);
					System.out.println(index + ". " + person.toString());
					
					lines.add(line); //이거 설명
					index++;
				}
				
				//읽기 스트림 종료
				br.close();
				
				System.out.println("삭제할 번호: ");
				int delIndex = sc.nextInt();
				sc.nextLine(); // 줄바꿈 문자 제거
				
				//변경된 목록을 파일에 다시 쓴다 (덮어쓰기)
				if(delIndex >= 1 && delIndex <= lines.size()) {
					lines.remove(delIndex - 1);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), "MS949"));
					for (String l : lines) {
						bw.write(l);
						bw.newLine();
					}
					bw.close();
					System.out.println("삭제되었습니다\n");
				}else {
					System.out.println("유효하지 않은 번호입니다\n");
				}
				
			}else if(menuNum==4) {
				System.out.println("<4.검색>");
				
				System.out.println("검색할 이름: ");
				String keyword = sc.nextLine();
				
				//읽기 스트림 준비
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH),"MS949"));
				String line;
				boolean found = false;
				int index = 1;
				
				while ((line = br.readLine()) != null) {
					if(line.contains(keyword)) {
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
				
				//읽기 스트림 종료
				br.close();
				
				if(!found) {
					System.out.println("일치하는 연락처가 없습니다");
				}
				
				System.out.println();
				
				
			}else if(menuNum==5) {
				System.out.println("<5.종료>");
				
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				System.out.println("다시 입력해주세요\n");
			}
			
		}
		
		//키보드 해제
		sc.close();
		
	}

}
