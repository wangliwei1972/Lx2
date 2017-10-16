package lx.sjtfcj.cc.lx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    private RadioButton rb;
    //private CheckBox checkBox;
    private TextView text1;
    private Button bu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text1 = (TextView)findViewById(R.id.text);
        bu1 = (Button)findViewById(R.id.button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checkBoxclickedlistener ch = new checkBoxclickedlistener();

        rb.setOnCheckedChangeListener(ch);
        bu1cliced bu =new bu1cliced();
        bu1.setOnClickListener(bu);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    class bu1cliced implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            text1.setText("xxxxxxxxxxxsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssxxxxxxxx");
            String ta ="";

            //声明Connection对象
            Connection con;
            //驱动程序名
            String driver = "com.mysql.jdbc.Driver";
            //URL指向要访问的数据库名mydata
            String url = "jdbc:mysql://192.168.1.100:3306/xsqk";
            //MySQL配置时的用户名
            String user = "root";
            //MySQL配置时的密码
            String password = "123654";
            //遍历查询结果集
            try {
                //加载驱动程序
                Class.forName(driver);
                //1.getConnection()方法，连接MySQL数据库！！
                con = DriverManager.getConnection(url,user,password);
                if(!con.isClosed())
                    System.out.println("Succeeded connecting to the Database!");
                //2.创建statement类对象，用来执行SQL语句！！
                Statement statement = con.createStatement();
                //要执行的SQL语句
                String sql = "select dz,mp,jzmj from hu1 limit 5";
                //3.ResultSet类，用来存放获取的结果集！！
                ResultSet rs = statement.executeQuery(sql);
                ta=ta+"------------------------------"+"\n";
                ta=ta+"执行结果如下所示:"+"\n";
                ta=ta+"-----------------"+"\n";
                ta=ta+"\t" +"  地址      "+"\t"  +  "\t" + "  门牌         "+"\t" + "面积"+"\n";
                ta=ta+"------------------------------"+"\n";

                String dz = null;
                String mp = null;
                String mj =null;
                while(rs.next()){
                    //获取stuname这列数据
                    dz = rs.getString("dz");
                    //获取stuid这列数据
                    mp = rs.getString("mp");
                    mj=rs.getString("jzmj");

                    //输出结果
                    System.out.println(dz+ "\t" + mp + "\t" + mj);
                    text1.setText(ta);
                }
                rs.close();
                con.close();
            } catch(ClassNotFoundException e) {
                //数据库驱动类异常处理
                System.out.println("Sorry,can`t find the Driver!");
                e.printStackTrace();
            } catch(SQLException e) {
                //数据库连接失败异常处理
                e.printStackTrace();
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally{
                System.out.println("数据库数据成功获取！！");

            }





        }
    }

    class checkBoxclickedlistener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if (rb.isChecked()) {
                text1.setText("checkBox is clicked");
            }
            else {
                text1.setText("checkBox is UnClicked");
            }
        }



   /*     public void onClick(View view) {
            view.setBackgroundColor(0X0000FF);
            CheckBox ck = (CheckBox)view;
            if (ck.isClickable()==true) {
                text1.setText("checkBox is clicked");
            } else {
                text1.setText("checkBox is UnClicked");
            }
        }
*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
