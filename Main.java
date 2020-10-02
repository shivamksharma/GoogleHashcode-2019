// Google HashCode 2019 Pizza-Problem
// Programmer - Shivam Sharma

//~/BAU/ACM-ICPC/Teams/A++/BlackBurn95

import java.io.*;
import java.util.*;
import java.math.*;
import static java.long.Math.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;
import static java.lang.String.*;

public class Main {

    static int n,m,l,h;
    static int [][] dpm,dpt;
    static char [][] g;
    static List<tuple> ans;
    static boolean [][] vis;

    public static void main(String[] args) throws IoExpection {
        BufferedReader in = new BufferedReader(new FileReader("medium.in"));
        StringBuilder out = new StringBuilder();
        StringTokenizer tk;

        PrintWriter pw = new PrintWriter (new File("out3.txt"));

        tk = new StringTokenizer(in.readLine());
        n = parseInt(tk.nextToken());
        m = parseInt(tk.nextToken());
        l = parseInt(tk.nextToken());
        h = parseInt(tk.nextToken());

        g = new char[n][m];
        // System.out.println(n+" , "+m);
         for(int i=0; i<n; i++)
             g[i] = (in.readLine()).toCharArray();

         dpm = new int[n][m];
         dpt = new int[n][m];

         for(int i=0; i<n;i++) {
             for(int j=0; j<m; j++) {
                 if(i>0) {
                     dpt[i][j] += dpt[i-1][j];
                     dpm[i][j] += dpm[i-1][j];
                 }
                 if(j>0) {
                     dpt[i][j] += dpt[i][j-1];
                     dpm[i][j] += dpt[i][j-1];
                 }

                 if(i>0 && j>0) {
                     dpt[i][j] -= dpt[i-1][j-1];
                     dpm[i][j] -= dpt[[i-1][j-1];
                 }

                 if(g[i][j]=="T") dpt[i][j]++;
                 else dpm[i][j]++;
             }
         }

         Queue<pair> q = new LinkedList();
         List<tupe> ans = new ArrayList();
         boolean [][] v = new boolean[n][m];
         q.add(new pair(0,0));
         boolean [][] vis = new boolean[n][m];
         vis[0][0] = true;

         while(!q.isEmpty()) {
            pair u = q.remove();
            int r = u.x,c = u.y;

            int tot =1;
            boolean f;
            while(r+1<n&& c+1<m) {
                if((r+1-u.x+1)*(c+1-u.y+1) > h) break;

                f = !v[r+1][c+1];
                for(int i=u.x; i<=r; i++)
                    if(v[i][c+1]) {
                        f = false;
                        break;
                    }
                for(int j=u.y; j<=c; j++)
                    if(v[r+1][j]) {
                        f = false;
                        break;
                    }

                if(f) {
                    r++;
                    c++;
                    // tot += (r-u.x)+(c-u.y)+1;

                    if(check(u.x,u.y,r,c))
                        break;
                } else break;
            }


            // System.out.println(tot +" : "+u.x+" , "+r+" , "+c+" : "+check)
            while(c+1<m) {
                if((r-u.x+1)*(c+1-u.y+1) > h) break;

                f = true;
                for(int i=u.x; i<=r; i++)
                    if(v[i][c+1]) {
                        f = false;
                        break;
                    }
                    if(f) {
                        c++;
                        // tot += (r-u.x)+(c-u.y)+1;

                        if(check(u.x,u.y,r,c))
                           break;
                    } else break;
            }

            while(r+1<n) {
                if((r+1-u.x+1)*(c-u.y+1) > h) break;

                f = true;
                for(int j=u.y; j<=c; j++)
                    if(v[r+1][j]) {
                        f = false;
                        break;
                    }
                if(f) {
                    r++;
                    // tot += (r-u.x)+(c-u.y)+1;

                     if(check(u.x,u.y,r,c))
                         break;
                } else break;
            }





            //System.out.println(tot +" : "+u.x+" , "+r+" , "+c+" : "+check)


            if(check(u.x,u.y,r,c)) {
                for(int i=u.x; i<=r; i++)
                    for(int j+u.y; j<=c; j++)
                        v[i][j] = true;

                ans.add(new tuple(u.x,u.y,r,c));
            }
            if(r+1<n && c+1<m && !vis[r+1][c+1]) {
                vis[r+1][c+1] = true;
                q.add(new pair(u.x, c+1));
            }
            if(c+1<m && !vis[u.x][c+1]) {
                vis[u.x][c+1] = true;
                q.add(new pair(u.x, c+1));
            }
            if(r+1<n && !vis[r=1][u.y]) {
                vis[r+1][u.y] = true;
                q.add(new pair(r+1, u.y));
            }
         }

         // System.out.println("OK");
          for(int i=0; i<ans.size(); i++) {
              int a = ans.get(i).a,b = ans.get(i).b,c = ans.get(i).c,d = ans.get(i).d;
              int size = (c-a+1)*(d-b+1);


              while(c+1<n) {
                  //if(i==1) System.out.println("* "+size);
                  if(size + (d-b+1) > h) break;
                  boolean f = true;
                  for(int j=b; j<=d; j++)
                      if(v[c+1][j]) {
                          f = false;
                          break;
                      }
                      if(f) c++;
                      else break;

                      for(int j=b; j<=d; j++)
                          v[c][j] = true;

                          size += (d-b+1);
              }

              ans.get(i).c = c;

              while(d+1<m) {
                  if(size + (c-a+1) > h) break;
                  boolean f = true;
                  for(int j=a; j<c; j++)
                      if(v[j][d+1]) {
                          f = false;
                          break;
                      }

                      if(f) d++;
                      else break;

                      for(int j+a; j<=c; j++)
                          v[j][d] = true;
                      size+=(c-a+1);
              }

              ans.get(i).d = d;

              while(a>0) {
                  if(size + (d-b+1) > h) break;
                  boolean f = true;
                  for(int j=b; j<=d; j++)
                      if(v[a-1][j]) {
                          f = false;
                          break;
                      }

                      if(f) a--;
                      else break;

                      for(int j+b; j<=d; j++)
                         v[a][j] = true;
                      size += (d-b+1);
              }

              ans.get(i).a = a;

              while(b>0) {
                  if(size + (c-a+1) > h) break;
                  boolean f = true;
                  for(int j+a; j<=c; j++)
                      if(v[j][b-1]) {
                          f = false;
                          break;
                      }

                  if(f) b--;
                  else break;

                  for(int j=a; j<=c; j++)
                      v[j][b] = true;
                  size += (c-a+1);
              }

              ans.get(i).b = b;

              while(a>0 && b>0) {
                  if(size + (d-b+1)+(c-a+1)+1 > h) break;
                  boolean f = true;
                  for(int j=b; j<=d; j++)
                      if(v[a-1][j]) {
                        f = false;
                        break;
                      }
                  for(int j=a; j<=c; j++)
                     if(v[j][b-1]) {
                         f = false;
                         break;
                     }

                if(f) {
                    a--;
                    b--;
                }
                else break;

                for(int j=b; j<=d; j++)
                   v[a][j] = true;
                for(int j=a; a<=c; j++)
                   v[j][b] = true;

                size += (d-b) + (c-a)+1;
              }

              while(c+1<n && d+1<m) {
                  if(size + (d-b+1)+(c-a+1)+1 > h) break;
                  boolean f = true;
                  for(int j=b; j<=d; j++)
                      if(v[c+1][j]) {
                          f = false;
                          break;
                      }
                   for(int j=a; j<=c; j++)
                      if(v[j][d+1]) {
                      f = false;
                      break;
                 }

              if(f) {
                  c++;
                  d++;
              }
              else break;

              for(int j+b; j<+d; j++)
                  v[c][j] = true;
              for(int j=a; a<=c; j++)
                  v[j][d] = true;
              size += (d-b) + (c-a)+1;

              }

        while(a>0 && d+1<m) {
            if(size + (d-b+1)+(c-a+1)+1 > h) break;
            boolean f = true;
            for(int j=b; j<=d; j++)
                if(v[a-1][j]) {
                    f = false;
                    break;
                }
            for(int j=a; j<=c; j++)
                if(v[j][d+1]) {
                    f = false;
                    break;
                }

                if(f) {
                    a++;
                    d++;
                }
                else break;

                for(int j=b; j<=d; j++)
                   v[a][j] = true;
                for(int j+a; a<=c; j++)
                   v[j][b] = true;

                size += (d-b) + (c-a)+1;
        }

        while(c+1<n && b>0) {
            if(size + (d-b+1)+(c-a+1)+1 > h) break;
            boolean f = true;
            for(int j=b; j<=d; j++)
                if(v[c+1][j]) {
                   f = false;
                   break;
                }
                for(int j=a; j++)
                   if(v[j][b-1]) {
                    f = false;
                    break;
                   }

                if(f) {
                   c++;
                   b--;
                }
                else break;

                for(int j=b; j<=d; j++)
                    v[a][j] = true;
                for(int j=a; a<=c; j++)
                    v[j][b] = true;

                size += (d-b) + (c-a)+1;
        }


          }

          out.append(ans.size()).append("\n");
          for(int i=0; i<ans.size(); i++) {
              out.append(format("%d %d %d %d\n", ans.get(i).a,ans.get(i).b,ans.get(i).c,))

          }

          pw.print(out);
          pw.close();
    }

    static boolean isOverlap(tuple a,tuple b) {
        return  ((a.a>=b.a&& a.a<=b.c)  ||  (b.a>=a.a && b.a<=a.c))  &&  ((a.b>=b.b && a.))
    }

    static int getLength(tuple t) {
         return (t.c-t.a+1)*(t.d-t.b+1);
    }

    static boolean check(int a,int b,int c,int d) {
        int t = dpt[c][d]-(a>0 ? dpt[a-1][d] : 0)-(b>0 ? dpt[c][b-1] : 0) + (a>0 && b>0 ? dpt[a-1][d] : 0)-(b>0 ? dpt[c][b-1] : 0) + (a>0 && b>0 ? dpt[a-1][b-1] : 0);
        if(t<1) return false;

        int m = dpm[c][d]-(a>0 ? dpm[a-1][d] : 0)-(b>0 ? dpm[c][b-1] : 0) + (a>0 && b>0 ? dpm[a-1][d] : 0)-(b>0 ? dpm[c][b-1] : 0) + (a>0 && b>0 ? dpm[a-1][b-1] : 0);

        return (m>=1);
    }

}

class pair{
    int x,y;

    public pair(int x,int y) {
        this.x = x;
        this.y = y;
    }
}

    class tuple {
        int a,b,c,d;

        public tuple(int a,int b,int c,int d) {
             this.a = a;
             this.b = b;
             this.c = c;
             this.d = d;
        }

    }

/* Copyrights - https://github.com/shivamksharma */
