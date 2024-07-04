#include<iostream>
#include<queue>
#include<tuple>
using namespace std;

int main() {
    int rows, columns;
    cin >> rows >> columns;

    // true = traversable
    // false = not traversable
    bool maze[rows][columns];
    bool path[rows][columns];
    int flood[rows][columns];
    int x_finish, y_finish;

    queue<tuple<int, int>> q;

    for (int y = 0; y < rows; y++) {
        string line;
        cin >> line;
        for (int x = 0; x < columns; x++) {
            maze[y][x] = (line[x] != '#');
            path[y][x] = false;
            if (line[x] == 'F') {
                x_finish = x;
                y_finish = y;
            }
            if (line[x] == 'S') {
                flood[y][x] = 0;
                q.push({x, y});
            } else {
                flood[y][x] = -1;
            }
        }
    }

    int dy[] = {-1, 1, 0, 0};
    int dx[] = {0, 0, 1, -1};

    while (!q.empty()) {
        int x, y;
        tie(x, y) = q.front();
        q.pop();

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= rows || nx < 0 || nx >= columns) continue;
            if (!maze[ny][nx]) continue;
            if (flood[ny][nx] == -1) {
                flood[ny][nx] = flood[y][x] + 1;

                if (ny == y_finish && nx == x_finish) {
                    cout << flood[ny][nx] << endl;
                    goto print;
                }
                q.push({nx, ny});
            }
        }
    }

    cout << "No Solution" << endl;
    exit(0);

print:
    int x = x_finish;
    int y = y_finish;
    path[y][x] = true;
    while (flood[y][x] != 0) {
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= rows || nx < 0 || nx >= columns) continue;
            if (flood[ny][nx] != flood[y][x] - 1) continue;

            path[ny][nx] = true;
            y = ny;
            x = nx;
        }
    }

    for (int y = 0; y < rows; y++) {
        for (int x = 0; x < columns; x++) {
            if (!maze[y][x]) cout << '#';
            else cout << (path[y][x] ? '.' : ' ');
        }
        cout << endl;
    }
}
