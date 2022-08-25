#include <stdio.h>
#include <string.h>

int main()
{
    printf("请安装Java / JDK 11及以上版本\n");
    char* commend_start_game = "java starter.GameStart";
    char* commend_check_java_exist = "java --version";
    int i = system(commend_check_java_exist);
    if (i !=0 )
    {
        system("cls");
        printf("=========================================\n");
        printf("未安装Java11或未设置环境变量\n");
        printf("=========================================\n");
        system("pause");
        return 1;
    }
    system(commend_start_game);


}
