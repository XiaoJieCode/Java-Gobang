#include <stdio.h>
#include <string.h>

int main()
{
    printf("�밲װJava / JDK 11�����ϰ汾\n");
    char* commend_start_game = "java starter.GameStart";
    char* commend_check_java_exist = "java --version";
    int i = system(commend_check_java_exist);
    if (i !=0 )
    {
        system("cls");
        printf("=========================================\n");
        printf("δ��װJava11��δ���û�������\n");
        printf("=========================================\n");
        system("pause");
        return 1;
    }
    system(commend_start_game);


}
