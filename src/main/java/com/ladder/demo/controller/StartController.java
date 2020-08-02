package com.ladder.demo.controller;

import com.ladder.demo.status.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class StartController {

    private List<List<Pair>> resCandidate;

    public StartController(){
        this.resCandidate = new ArrayList<>();
    }

    //초기 화면의 구상
    @GetMapping("/")
    public String startProject(HttpServletRequest request){
        return "game_index";
    }


    @PostMapping("/start/count")
    @ResponseBody
    public List<Integer> count(@RequestParam("id") String id){
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);

        System.out.println(id);

        return list;
    }
    
    /*
     * 만들어진 사다리가 유효한 사다리인지 판별하는 함수
     */
    public boolean simulate(int[][] map,int maxRow, int maxCol){
        int[] check = new int[4];//초기값은 0, 유저가 도착하면 1의 값
        int currentLocation = 0;

        //check을 초기화
        Arrays.fill(check,0);

        for(int i = 0; i < maxCol; i++){
            currentLocation = i;
            //사다리를 타기 시작
            for(int j = 0 ; j < maxRow; j++){
                //양쪽을 조사
                if(currentLocation != 0 && currentLocation != (maxCol-1)){
                    if(map[j][currentLocation] == 1){
                        currentLocation += 1;
                    }
                    else if(map[j][currentLocation-1] == 1){
                        currentLocation -= 1;
                    }
                }
                //오른쪽을 조사
                else if(currentLocation == 0){
                    if(map[j][currentLocation] == 1){
                        currentLocation += 1;
                    }
                }
                //왼쪽을 조사
                else if(currentLocation == (maxCol-1)){
                    if(map[j][currentLocation-1] == 1){
                        currentLocation -= 1;
                    }
                }
            }

            //사다리를 다 타고 내려온 후의 상태를 확인
            if(check[currentLocation] == 1){
                return false;
            }
            else{
                check[currentLocation] = 1;
            }
        }
        return true;
    }

    /*
     *사다리를 만들기 위해 dfs탐색을 하는 함수
     */
    public void dfs(int r, int c, int[][] map,int maxRow, int maxCol){

        if(r == (maxRow-1) && c == (maxCol-1)){
            //시뮬레이션을 통과해야 후보군에 저장
            if(simulate(map,maxRow,maxCol)){
//                Map<Integer,Integer> insert = new HashMap<>();
                List<Pair> insert =  new ArrayList<>();
                for(int i = 0 ; i < maxRow; i++){
                    for(int j = 0; j < maxCol; j++){
                        if(map[i][j] == 1){
                            Pair pair = new Pair(i,j);
                            insert.add(pair);
                        }
                    }
                }
                //최종 후보군에 저장
                this.resCandidate.add(insert);
            }
            return;
        }

        if(c == 0){
            //해당 지점에 사다리를 놓는 경우
            map[r][c] = 1;
            dfs(r,c+1,map,maxRow,maxCol);
            map[r][c] = 0;

            //해당 지점에 사다리를 놓지 않는 경우
            map[r][c] = 0;
            dfs(r,c+1,map,maxRow,maxCol);
            map[r][c] = 0;
        }
        else if(c == (maxCol-1)){
            dfs(r+1,0,map,maxRow,maxCol);

        }
        else{
            //사다리를 놓을 수 있음
            if(map[r][c-1] == 0){
                //해당 지점에 사다리를 놓는 경우
                map[r][c] = 1;
                dfs(r,c+1,map,maxRow,maxCol);
                map[r][c] = 0;

                //해당 지점에 사다리를 놓지 않는 경우
                map[r][c] = 0;
                dfs(r,c+1,map,maxRow,maxCol);
                map[r][c] = 0;
            }
            //사다리를 놓을 수 없음
            else{
                map[r][c] = 0;
                dfs(r,c+1,map,maxRow,maxCol);
                map[r][c] = 0;
            }
        }
    }

    //start버튼을 눌렀을 때의 컨트롤러
    @PostMapping("/start/ladder")
    @ResponseBody
    public List<Pair> simulateLadder(@RequestParam("userNum") int userNum){
        List<Pair> res;

        int rowNum = userNum + 3;
        int colNum = userNum;

        //연결 상태의 정보를 저장하는 Map의 생성
        int[][] map = new int[rowNum][colNum];

        //map을 초기화
        for(int[] row:map) Arrays.fill(row,0);

        dfs(0,0,map,rowNum,colNum);

        //최종 후보군에서 하나를 뽑아냄
        int pick = (int)(Math.random() * this.resCandidate.size());

        res = this.resCandidate.get(pick);

        return res;
    }

    @GetMapping("start/result")
    public String result(){
        return "game_index01";
    }


}
