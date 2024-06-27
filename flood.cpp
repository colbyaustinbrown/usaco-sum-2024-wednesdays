#include<iostream>
using namespace std;

int main() {
    int rows, columns;
    cin >> rows >> columns;

    // true = traversable
    // false = not traversable
    bool maze[rows][columns];
    int flood[rows][columns];
    int x_finish, y_finish;

    for (int y = 0; y < rows; y++) {
        string line;
        cin >> line;
        for (int x = 0; x < columns; x++) {
            maze[y][x] = (line[x] != '#');
            if (line[x] == 'F') {
                x_finish = x;
                y_finish = y;
            }
            if (line[x] == 'S') {
                flood[y][x] = 0;
            } else {
                flood[y][x] = -1;
            }
        }
    }

    int dy[] = {-1, 1, 0, 0};
    int dx[] = {0, 0, 1, -1};

    for (int step = 1; flood[y_finish][x_finish] == -1; step++) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (!maze[y][x]) continue;

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (ny < 0 || ny >= rows || nx < 0 || nx >= columns) continue;
                    if (flood[ny][nx] == step - 1) {
                        flood[y][x] = step;
                    }
                }
            }
        }
    }

    cout << flood[y_finish][x_finish] << endl;
}
