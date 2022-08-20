// 开始游戏.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <stdio.h>
#include <string.h>

int main()
{
    char* commend_start_game = "java starter.GameStart";
    char* commend_check_java_exist = "java --version";
    int i = system(commend_check_java_exist);
    if (i !=0 ) 
    {
        system("cls");
        printf("=========================================\n");
        printf("未安装Java或未设置环境变量\n");
        printf("=========================================\n");
        return 1;
  
    }
    system(commend_start_game);

    
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
