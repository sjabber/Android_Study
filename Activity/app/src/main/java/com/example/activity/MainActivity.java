package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    // Activity가 생성될 때 자동으로 호출된다.
    // 화면 회전이 발생했을 때 자동으로 호출된다.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //System.out.println("onCreate 메서드 호출");
        Log.d("test", "onCreate 호출");
    }

    @Override
    // onCreate 메소드 호출 이후에 자동으로 호출
    // Activity가 정지상태가 되었다가 활동상태로 돌아올때 자동으로 호출
    // app실행하다 홈버튼 누르면 app이 정지상태로 돌아감. 다시 app을 실행하면 활동상태가 되면서
    // onStart가 실행된다.
    protected void onStart() {
        super.onStart();

        Log.d("test2", "onStart호출");
    }

    @Override
    // onStart메소드가 호출된 이후에 자동으로 호출된다.
    // Activity가 일시 정지 되었다가 "다시 돌아올때" 호출
    // 일시정지와 정지의 차이
    // 정지상태 -> 화면이 안보일때
    // 일시정지상태 -> 팝업창같은것이 뜬 경우에
    protected void onResume() {
        super.onResume();
        Log.d("test3", "onReusme 호출");
    }

    @Override
    // Activity가 정지상태가 되었다가 활동 상태로 돌아갈 때 onStart메소드
    // 전에 호출된다.
    protected void onRestart() {
        super.onRestart();

        Log.d("test4", "onRestart 호출");
    }

    @Override
    // Activity가 일시 정지 상태가 될때 호출된다.
    // 화면상에서 완전히 사라지거나 현재 화면 위에 작은 팝업창 같은것이 나타날 떄 호출

    protected void onPause() {
        super.onPause();

        Log.d("test5", "onPause 호출");
    }

    @Override
    // Activity가 화면에서 사라질 때 호출된다.
    protected void onStop() {
        super.onStop();

        Log.d("test6", "onStop 호출");
    }

    @Override
    // 현재 Activity의 수행이 완전히 종료되어 메모리상에서 제거될 때 호출
    protected void onDestroy() {
        super.onDestroy();

        Log.d("test", "onDestroy");
    }

    // 보통 onCreate같은 경우 Activity가 실행될 때 최초에 딱 한번만 실행되기 때문에
    // 전체 Acitivity에서 사용하는 객체라던가 자원같은걸 구할때 onCreate 안에 구현하여 사용한다.
    // 일시정지가 될때 어떠한 상황이던지간에 onPuase가 무조건 호출된다.
    // 어떤 상황이던지간에 현재 액티비티가 사용되지 못하는상황에서 무언가를 처리해야한 경우에는
    // onPuase에다가 구현해주면된다.
    // App이 제거될때 무언가 구현하겠다 할 경우에는 onDestroy안에서 구현하면 된다.
    //여러가지 자원관리할 경우에 사용하기 좋다.
}