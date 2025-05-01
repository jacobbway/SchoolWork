#include <iostream>
#include <vector>

using namespace std;

//insertion sort method by row
void sortMemo(vector<vector<int>> &memo){
    
    for(int i = 1; i < memo.size(); i++){
        int j = i - 1;

        //determing my ratios would need adjusting if it was a 3d array for sure
        double rat1 = memo[j][0]/memo[j][1];
        double rat2 = memo[i][0]/memo[i][1];

        if(rat2 > rat1){
            swap(memo[j][0], memo[i][0]);
            swap(memo[j][1], memo[i][1]);
        }
    }
}
//prints my total profit
void profCalc(vector<vector<int>> &memo, int wt){
    int i = 0;
    double total = 0;
    while(wt != 0 && i < memo.size()){
        if(memo[i][1] < wt){
            total += memo[i][0];
            wt -= memo[i][1];
        }
        else{
            total += wt * memo[i][0]/memo[i][1];
            wt = 0;
        }
        i++;
    }

    cout << "\nRemaing Weight: " << wt << endl;
    cout << "Total Profit: " << total << endl;
}

//prints my 2d vector
void printVec(vector<vector<int>> memo){
    cout << endl;
    for(int i = 0; i < memo.size(); i++){
        for(int j = 0; j < memo[i].size(); j++){
            cout << memo[i][j] << " ";
        }
        cout << endl;
    }
}


int main(){
    vector<vector<int>> memo = {{60, 10}, {120, 30}, {100, 20}};
    int wt = 50;

    printVec(memo);
    sortMemo(memo);
    printVec(memo);
    profCalc(memo, wt);

    return 0;
}