package com.baseball.webgame.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.AllArgsConstructor;
import lombok.Data;


@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> users = new ArrayList<WebSocketSession>();
	private Map<String, UserInfo> userMap = new HashMap<String,UserInfo>();
	private List<String> match = new ArrayList<>();

	public List<Integer> stringToIntList(String intStr){
        String str = intStr.replaceAll(" ", "").replaceAll("", " ").trim();
        String tmp[] = str.split(" ");
		List<Integer> number = new ArrayList<>();
		for (String is : tmp)
			number.add(Integer.parseInt(is));
		return number;
    }

    public String compare(List<Integer> correct, List<Integer> answer) {
		String result = "";
		int s = 0;
		int b = 0;
		for (int i = 0; i < answer.size(); i++) {
			if(correct.get(i)==answer.get(i)){
                s++;
            }else if(correct.contains(answer.get(i))){
                b++;
            }
		}
		if (s == 0 && b == 0)
			result = "Out";
		else {
			if(s != 0)
				result += s + "S";
        	if(b != 0)
            	result += b + "B";
		}

		return result;
	}

	@Data
	@AllArgsConstructor
	class UserInfo{
		String number;
		Object obj;
	}
	
	@Override
        //소켓 연결 생성 후 실행 메서드
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("TextWebSocketHandler : 연결 생성!");
		users.add(session);
	}

	@Override
    //메시지 수신 후 실행 메서드
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("TextWebSocketHandler : 메시지 수신!");
		System.out.println("메시지 : " + message.getPayload());
		JSONObject object = new JSONObject(message.getPayload());
		String type = object.getString("type");
		//등록 요청 메시지
		if(type != null && type.equals("register") ) {
			String user = object.getString("userid");
			//아이디랑 Session이랑 매핑
			userMap.put(user, new UserInfo("", session));
			match.add(user);
			//매치 대기중인 유저가 2명이 되면 target을 서로 지정
			if(match.size()==2){
				WebSocketSession ws1 = (WebSocketSession)userMap.get(match.get(0)).getObj();
				WebSocketSession ws2 = (WebSocketSession)userMap.get(match.get(1)).getObj();
				ws1.sendMessage(new TextMessage(match.get(1)+"un"));
				ws2.sendMessage(new TextMessage(match.get(0)+"un"));
				match.clear();
			}
			//상대가 맞출 숫자를 입력받아 저장
		}else if(type != null && type.equals("numberRegister") ) {
			String user = object.getString("userid");
			String number = object.getString("userNumber");
			//아이디랑 Session이랑 매핑
			userMap.get(user).setNumber(number);
		
		//승리. 상대에게 승리 알림
		}else if(type.equals("win")){
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target).getObj();
			String msg = object.getString("message");
            ws.sendMessage(new TextMessage(msg));
			//정답 입력시 작동
        }else if(type.equals("answer")){
			String user = object.getString("userid");
			WebSocketSession rws = (WebSocketSession)userMap.get(user).getObj();
			
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target).getObj();
			String msg = object.getString("message");
			String from = object.getString("userid");
			String compare = compare(stringToIntList(userMap.get(target).getNumber()), stringToIntList(msg));
			if(ws !=null ) {
				rws.sendMessage(new TextMessage("<p style='text-align:right;'>"+compare+"</p>"));
				if(compare.equals("3S"))
					ws.sendMessage(new TextMessage("<p style='text-align:center;'>"+from+" Win</p>"));
				else
					ws.sendMessage(new TextMessage("<p style='text-align:left;'>"+from+" : "+msg+"<br/>"+compare+"</p>"));
			}
		}
	}

}