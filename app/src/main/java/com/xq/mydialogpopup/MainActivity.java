package com.xq.mydialogpopup;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements MyDialog.OnDialogItemClickListener {


    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiBtnDialog();
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceDialog();
            }
        });
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiChoiceDialog();
            }
        });
        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaitingDialog();
            }
        });
        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });
        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        findViewById(R.id.btn10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new MyDialog(MainActivity.this, R.layout.dialog_layout,
                        new String[]{"内容", "取消", "确定"},
                        new int[]{R.id.dialog_text, R.id.dialog_cancel, R.id.dialog_sure});
                myDialog.setOnDialogItemClickListener(MainActivity.this);
                myDialog.show();
            }
        });

        findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new MyDialog(MainActivity.this, R.layout.loading_layout,
                        new String[]{"视频地址解析中..."},
                        new int[]{R.id.textView});
                myDialog.setOnDialogItemClickListener(MainActivity.this);
                myDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myDialog.dismiss();
                    }
                }, 2000);
            }
        });

        findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new MyDialog(MainActivity.this, R.layout.loading1_layout, null, null);
                myDialog.setOnDialogItemClickListener(MainActivity.this);
                myDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myDialog.dismiss();
                    }
                }, 2000);
            }
        });
    }

    /**
     * 一般对话框
     */
    private void showNormalDialog() {
    /* @setIcon 设置对话框图标
     * @setTitle 设置对话框标题
     * @setMessage 设置对话框消息提示
     * setXXX方法返回Dialog对象，因此可以链式设置属性
     */
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("标题")
                .setMessage("内容")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
        dialog.show();

        // 在dialog执行show之后才能来设置-----------------------------------------------------修改文字颜色及大小--------------
        TextView tvMsg = (TextView) dialog.findViewById(android.R.id.message);
        tvMsg.setTextSize(16);
        tvMsg.setTextColor(Color.parseColor("#0000ff"));

        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextSize(16);
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ff0000"));

        dialog.getButton(dialog.BUTTON_POSITIVE).setTextSize(16);
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00ff00"));

        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object alertController = mAlert.get(dialog);

            Field mTitleView = alertController.getClass().getDeclaredField("mTitleView");
            mTitleView.setAccessible(true);

            TextView tvTitle = (TextView) mTitleView.get(alertController);
            if (null != tvTitle) {
                tvTitle.setTextSize(16);
                tvTitle.setTextColor(Color.parseColor("#ff8041"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 三个按钮对话框
     * setNeutralButton
     */
    public void showMultiBtnDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("我是一个普通Dialog").setMessage("你要点击哪一个按钮呢?");
        builder.setIcon(R.mipmap.ic_launcher);

        builder.setNegativeButton("按钮1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "按钮1", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("按钮2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "按钮2", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("按钮3", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "按钮3", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    /**
     * 列表对话框
     */
    private void showListDialog() {
        final String[] items = {"我是1", "我是2", "我是3", "我是4", "我是1", "我是2", "我是3", "我是4", "我是1", "我是2", "我是3", "我是4"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(MainActivity.this);
        listDialog.setTitle("我是一个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                Toast.makeText(MainActivity.this,
                        "你点击了" + items[which],
                        Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = listDialog.create();
        dialog.show();

        //以下这些代码时用来设置dialog的高度，一定要放在show方法之后。--------------------------修改高度----------------
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = 800;
            window.setAttributes(attributes);
        }
    }

    /**
     * 单选对话框
     */
    int yourChoice;

    private void showSingleChoiceDialog() {
        final String[] items = {"我是1", "我是2", "我是3", "我是4"};
        yourChoice = -1;
        AlertDialog.Builder singleDialog = new AlertDialog.Builder(this);
        singleDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yourChoice = which;
            }
        });

        singleDialog.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (yourChoice != -1) {
                    Toast.makeText(MainActivity.this,
                            "你选择了" + items[yourChoice],
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        singleDialog.show();
    }

    /**
     * 多选对话框
     */
    private ArrayList<Integer> yourChoices = new ArrayList<>();

    private void showMultiChoiceDialog() {
        yourChoices.clear();
        final String[] items = {"我是1", "我是2", "我是3", "我是4"};
        AlertDialog.Builder multiChoiceDialog = new AlertDialog.Builder(this);
        // 设置默认选中的选项，全为false默认均未选中
        final boolean initChoiceSets[] = {false, false, false, false};
        multiChoiceDialog.setTitle("我是一个多选Dialog");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                System.out.println("which========" + which);
                if (isChecked) {
                    yourChoices.add(which);
                } else {
                    yourChoices.remove(which);
                }
            }
        });

        multiChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = yourChoices.size();
                String str = "";
                for (int i = 0; i < size; i++) {
                    str += items[yourChoices.get(i)] + " ";
                }
                Toast.makeText(MainActivity.this,
                        "你选中了" + str,
                        Toast.LENGTH_SHORT).show();
            }
        });
        multiChoiceDialog.show();

    }

    /**
     * 等待对话框
     */
    private void showWaitingDialog() {
         /* 等待Dialog具有屏蔽其他控件的交互能力
           * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
           * 下载等事件完成后，主动调用函数关闭该Dialog
           */
        final ProgressDialog waitingDialog = new ProgressDialog(MainActivity.this);
        waitingDialog.setTitle("我是一个等待Dialog");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                waitingDialog.dismiss();
                Toast.makeText(MainActivity.this,
                        "加载数据完成",
                        Toast.LENGTH_SHORT).show();
            }
        }, 10000);
    }

    /**
     * 进度条对话框
     */
    private void showProgressDialog() {
  /* @setProgress 设置初始进度
   * @setProgressStyle 设置样式（水平进度条）
   * @setMax 设置进度最大值
   */
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
          /* 模拟进度增加的过程
           * 新开一个线程，每个100ms，进度增加1
           */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX_PROGRESS) {
                    try {
                        Thread.sleep(50);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }

    /**
     * 编辑对话框
     *
     * @setView 装入一个EditView
     */
    private void showInputDialog() {
        final EditText et = new EditText(this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(this);
        inputDialog.setTitle("我是可以输入的对话框");
        inputDialog.setView(et);
        inputDialog.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), et.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        inputDialog.show();

    }

    private void showCustomDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_layout, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

        view.findViewById(R.id.imageColse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            window.setAttributes(lp);
        }
    }

    @Override
    public void OnDialogItemClick(MyDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_cancel:
                myDialog.dismiss();
                Log.e("111111", "您点击了取消！！！");
                break;

            case R.id.dialog_sure:
                myDialog.dismiss();
                Log.e("111111", "您点击了确定。。。");
                break;
        }
    }
}
