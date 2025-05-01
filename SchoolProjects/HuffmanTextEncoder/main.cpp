#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>
#include <queue>
#include <vector>

using namespace std;

//Node for tree
struct Node{
    char ch;
    int freq;
    Node *left, *right;
    Node(char c, int f) : ch(c), freq(f), left(nullptr), right(nullptr) {}
    Node(int f, Node* l, Node* r) : ch('\0'), freq(f), left(l), right(r) {}
};

//the compare function i am putting into my priority queue
struct Compare {
    bool operator()(Node* a, Node* b){
        return a->freq > b->freq;
    }
};

//i am building a map that is key char and a value of string
void buildEncodingMap(Node* root, unordered_map<char, string> &codeMap, string str = ""){
    //base case checks if the node is nullptr
    if(!root)
        return;
    //checks if the node is a leaf node
    //then if it is saves that characters encoded value to the map
    if(!root->left && !root->right){
        codeMap[root->ch] = str;
    }

    //adds a 0 to string if traveres left and a 1 if it goes right
    buildEncodingMap(root->left, codeMap, str + "0");
    buildEncodingMap(root->right, codeMap, str + "1");
}

//here i just print the value by searching the map for the current char from the
//input string and then it adds the string from map which is the encoded char
string encodeString(const string &text, unordered_map<char, string> &codeMap){
    string encoded = "";
    for(char c : text){
        encoded += codeMap[c];
    }

    return encoded;
}



int main(){
    string inFile = "gettysburg.txt";
    string address;

    try{
        ifstream input(inFile);

        if(input.is_open()){
            cout << "File Opened Succesfully\n";
        }
        string line;
        while(getline(input, line)){
            address += "\n" + line;
        }

        input.close();
    } catch(const ios_base::failure IO){
        cout << "File Didn't Open!\n";
        return 0;
    }

    //build my map that. should create a new pair if the char already exists in the map
    //and add a new pair if it doesnt
    unordered_map<char, int> freqMap;
    for(char c : address){
        freqMap[c]++;
    }

    //creates a queue that is minHeap which means ascending order or that 
    //that the top node in the queue should have the smallest frequency
    //that what the Compare we pass in does.
    priority_queue<Node*, vector<Node*>, Compare> pq;
    for(auto &pair : freqMap){
        pq.push(new Node(pair.first, pair.second));
    }

    //merges nodes until there is only one node left in the pq
    //which should be the root node of the binary tree
    while(pq.size() > 1) {
        Node *left = pq.top(); pq.pop();
        Node *right = pq.top(); pq.pop();
        Node *merged = new Node(left->freq + right->freq, left, right);
        pq.push(merged);
    }

    //creating a node that is the root node of the binary tree
    Node *root = pq.top();

    unordered_map<char, string> codeMap;
    buildEncodingMap(root, codeMap);

    //printing my encoded characters
    cout << "Encoded Characters:\n";
    for(auto &pair : codeMap){
        cout << "'" << pair.first << "': " << pair.second << "\n";
    }

    string encoded = encodeString(address, codeMap);
    cout << "\nEncoded String: \n" << encoded << endl;
}