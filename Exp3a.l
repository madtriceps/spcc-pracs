%{
    int newline_count=0;
    int space_count=;
    int word_count=;

%}

%%
\n {newline_count++;}
[]{space_count++;}
[a-zA-Z]+{word_count++;}
%%


int yywrap(){
    return 1;
}
int main(){
    yylex();
    printf("Number of newlines: %d\n", newline_count);
    printf("Number of spaces: %d\n", space_count);
    printf("Number of words: %d\n", word_count);
    return 0;
}