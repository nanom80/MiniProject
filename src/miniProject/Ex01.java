package miniProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ex01 {
	
	public static void main(String[] args) throws IOException {
		
		//읽기 스트림 준비
		InputStream in = new FileInputStream("C:\\javaStudy\\workspace\\MiniProject\\PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(in, "MS949");
		BufferedReader br = new BufferedReader(isr);
		
		//System.out.println("-----읽기 스트림 준비 완료-----");
		
		//쓰기 스트림 준비
		OutputStream out = new FileOutputStream("C:\\\\javaStudy\\\\workspace\\\\MiniProject\\\\PhoneDB_copy.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out, "MS949");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//System.out.println("-----쓰기 스트림 준비 완료-----");
		
		//키보드 연결
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("**************************************");
			System.out.println("*       전화번호 관리 프로그램       *");
			System.out.println("**************************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println(">메뉴번호:");
			
			int menuNum = sc.nextInt();	//입력대기
			
			System.out.println("입력한 값은 "+menuNum+"입니다.");
			
			if(menuNum==1) {
				System.out.println("<1.리스트>");
				
				
			}else if(menuNum==2) {
				System.out.println("<2.등록>");
				
			}else if(menuNum==3) {
				System.out.println("<3.삭제>");
				
			}else if(menuNum==4) {
				System.out.println("<4.검색>");
				
			}else if(menuNum==5) {
				System.out.println("<5.종료>");
			}
			
			
			String str = br.readLine();
			
			if(str == null) {
				System.out.println("-----파일 읽기 끝-----");
				break;
			}
			
			//파일 내용 복사
			bw.write(str);
			bw.newLine();
			
			//데이타 출력
			String[] info = str.split(",");
			
			String name = info[0];
			String hp = info[1];
			String company = info.length>=3 ? info[2] : "-";
			
			System.out.println("이름:"+name);
			System.out.println("핸드폰:"+hp);
			System.out.println("회사:"+company);
			System.out.println();
		}
		
		//키보드 해제
		sc.close();
		
		//스트림 닫기
		bw.close();
		br.close();
		System.out.println("-----스트림 닫기-----");
		System.out.println();
		
		/*
		//읽기 스트림 준비
		InputStream inCopy = new FileInputStream("C:\\javaStudy\\workspace\\MiniProject\\PhoneDB_copy.txt");
		InputStreamReader isrCopy = new InputStreamReader(inCopy, "MS949");
		BufferedReader brCopy = new BufferedReader(isrCopy);
		
		System.out.println("-----읽기 스트림 준비 완료-----");
		
		while(true) {
			String str = brCopy.readLine();
			
			if(str == null) {
				System.out.println("-----파일 읽기 끝-----");
				break;
			}
			
			//데이타 출력
			String[] info = str.split(",");
			
			String name = info[0];
			String hp = info[1];
			String company = info.length >= 3 ? info[2] : "-";
			
			System.out.println("이름: " + name);
            System.out.println("핸드폰: " + hp);
            System.out.println("회사: " + company);
            System.out.println();
		}
		
		brCopy.close();
		*/
		
		System.out.println("프로그램 종료");
		
	}
	
}