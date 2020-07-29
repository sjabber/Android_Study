package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조 변수
    TextView text1;
    ListView list1;
    //리스트 뷰를 구성하기 위한 문자열 배열
    String [] data1 = {
            "항목1", "항목2", "항목3", "항목4", "항목5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //뷰의 주소 값을 받아온다.
        text1 = (TextView)findViewById(R.id.textView);
        list1 =(ListView)findViewById(R.id.list1);
        // 뷰에 컨텍스트 메뉴를 설정한다.

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
        list1.setAdapter(adapter);

        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);


        // 뷰에 컨텍스트 메뉴를 설정한다.
        registerForContextMenu(text1); //텍스트 뷰를 길게 누르면 컨텍스트 메뉴가 나온다.
        registerForContextMenu(list1);

    }
    // 컨텍스트 메뉴가 설정되어 있는 뷰를 길게 누르면 컨텍스트 메뉴 구성을 위해서 호출하는 메서드
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // menu 객체를 통해서 메뉴를 구성해주면 된다.
        // 컨텍스트 메뉴를 길게 누른 객체의 주소값이 View 객체에 들어간다.
        // 즉, 사용자가 길게 누르면 뷰의 주소값을 얻어온다.
        // 길게눌렀을 때 나오는 컨텍스트 메뉴에 대한 정를 가지고 있는 객체는 menuInfo 이다.
        // 몇번째 항목을 길게 눌렀는지 정보가 여기에 들어있다!!
        // 여기에서 인덱스값을 가지고 오면 된다. 리스트의 몇번째 항목을 눌렀는지 알 수 있다.
        MenuInflater inflater = getMenuInflater();

        // 사용자가 길게 누르면 뷰의 주소값을 얻어온다.
        int view_id = v.getId();

        switch (view_id){
            case R.id.textView :
                menu.setHeaderIcon(R.mipmap.ic_launcher); //버전에 따라서 아이콘이 없을 수도 있다.
                menu.setHeaderTitle("텍스트 뷰의 컨텍스트 메뉴");
                inflater.inflate(R.menu.textview_menu, menu);
                break;
            case R.id.list1 :
                //사용자가 길게 누른 항목의 인덱스를 가져온다.
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                //AdapterContextMenuInfo 가 위의 ContextMenuInfo 를 상속받고 있다.
                // int position = info.position;


                menu.setHeaderTitle("리스트 뷰의 메뉴 : " + info.position);
                //이로써 몇번째 항목을 길게 누른것인지를 표시한다.

                inflater.inflate(R.menu.listview_menu, menu);
                break;
        }
    }

    //컨텍스트 메뉴의 항목을 터치하면 호출되는 메서드
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        // 사용자가 선택한 메뉴 항목의 id를 추출한다.
        int id = item.getItemId();

        // 메뉴를 터치했을때 그 메뉴안의 인덱스번호도 파악하려한다면?
        // MenuItem 의 item 으로부터 이를 또 뽑아낼 수 있다.

        // 리스트뷰의 항목을 길게 눌렀을 경우 항목의 인덱스 번호를 가지고 있는 객체를 추출한다.

        // 사용자가 길게 누른 리스트의 항목 인덱스를 가지고 있는 객체를 추출한다.
        ContextMenu.ContextMenuInfo info = item.getMenuInfo();
        int position = 0;
        if(info != null && info instanceof AdapterView.AdapterContextMenuInfo) {
            AdapterView.AdapterContextMenuInfo info2 = (AdapterView.AdapterContextMenuInfo)info;
            position = info2.position;
            // 위와 다른점은 위는 리스트의 항목중 하나를 길게 눌렀을 경우 해당항목의
            // 포지션이 몇번인지를 ContextMenu 에 표시하는 것이고
            // 현재 작업은 뜬 ContextMenu 의 항목중 눌린 항목이 몇번(포지션)인지를 표시하는 작업이다.
        }



        switch (id) {
            case R.id.text_item1 :
                text1.setText("텍스트뷰의 메뉴1을 선택하였습니다.");
                break;
            case R.id.text_item2 :
                text1.setText("텍스트뷰의 메뉴2를 선택하였습니다");
                break;
            case R.id.list_item1 :
                text1.setText("리스트뷰의 메뉴1을 선택하였습니다. \n" + "누른 항목은 " + (position+1)+ "번 입니다.");
                break;
            case R.id.list_item2 :
                text1.setText("리스트뷰의 메뉴2를 선택하였습니다. \n" + "누른 항목은 " +(position+1)+ "번 입니다.");
                break;
                // 리스트뷰는 다른뷰랑 다르게 리스트뷰 자체가 아닌 항목에따라서 다르게 나온다.
                // 즉, 리스트뷰는 사용자가 어떤 항목을 눌렀는가가 매우 중요하다.
        }

        return super.onContextItemSelected(item);
    }

    // 리스트뷰의 리스터
    class ListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            text1.setText("Item click : " + data1[position]);
        }
    }
}