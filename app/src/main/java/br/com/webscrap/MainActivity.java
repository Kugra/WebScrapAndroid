package br.com.webscrap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.webscrap.adapter.MyAdapter;
import br.com.webscrap.adapter.MyAdapterMain;
import br.com.webscrap.model.Casa;
import br.com.webscrap.model.Evento;

public class MainActivity extends AppCompatActivity {

    private List<Casa> casas;

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog mProgressDialog;
    private ListView lv;
    private Casa casa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casa = (Casa) getIntent().getSerializableExtra("extra");

        Agenda agenda = new Agenda();

        switch (casa.getCodigo()){
            case 0:
                //agenda.Beco203RSGetEachLinkDoInBackground();
                agenda.execute();
                break;
            case 1:
                agenda.CasaDoLadoGetEachLinkDoInBackground();
                break;
            case 2:
                agenda.CuckoGetEachLinkDoInBackground();
                break;
            case 3:
                agenda.SinnersGetEachLinkDoInBackground();
        }

        /*
        new Agenda().execute();

        lv = (ListView) findViewById(R.id.listview);

        findViewById(R.id.btn_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Agenda().execute();
            }
        });

        findViewById(R.id.btn_agenda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Agenda().execute();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = (String) adapterView.getItemAtPosition(i);
                startActivity(new Intent(MainActivity.this, InfoActivity.class).putExtra("key", url));
            }
        });
        */
    }

    private class Agenda extends AsyncTask<Void, Void, Void> {


//        List<String> agendaCucko = new ArrayList<>();
//        List<String> agendaSinners = new ArrayList<>();
//        List<String> agendaBeco203RS = new ArrayList<>();
//        List<String> agendaCasaDoLado = new ArrayList<>();

        List<Evento> listaEventos = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setTitle("Atenção");
            mProgressDialog.setMessage("Aguarde, carregando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        public void CuckoGetEachLinkDoInBackground(){
            /*try {
                Document document = Jsoup.connect("http://www.cucko.com.br/agenda/").get();
                Elements links = document.select("a[href~=(agenda/evento)]");

                for (Element link : links){
//                    agendaCucko.add("http://www.cucko.com.br/" + link.attr("href"));
                    listaEventos.add("http://www.cucko.com.br/" + link.attr("href"));
//                    System.out.println("LINK IMAGEM: http://www.cucko.com.br/"+link.select("img[src~=(uploads/eventos/imagem/)]").attr("src"));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

        public void SinnersGetEachLinkDoInBackground(){
           /* try{
                Document document = Jsoup.connect("http://www.sinnersclub.com.br").get();
                Elements links =  document.select("a[href~=(/agenda/)]");
                for(Element link : links){
//                    agendaSinners.add(link.attr("href"));
                    listaEventos.add(link.attr("href"));
//                    System.out.println("LINK IMAGEM: http://www.sinnersclub.com.br"+link.attr("cover-image"));
                }
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }

        public void Beco203RSGetEachLinkDoInBackground(){
            try{
                //Variavel 'ajuste' utilizada para retirar ".." ao inicio de cada link.
                //Remove utilizado para retirar link de aniversário e ingresso antecipado.
                Document document = Jsoup.connect("http://www.beco203.com.br/agenda/").get();
                Elements links =  document.select("a[href~=(agenda/)]");

                links.remove(links.last());
                links.remove(links.last());

                for(Element link : links){
                    String ajusteImg = link.select("img[src~=(/resources/conteudos/imagens/agenda/thumb/)]").attr("src");
                    ajusteImg = ajusteImg.substring(2,ajusteImg.length());
                    System.out.println("http://www.beco203.com.br"+ajusteImg);

                    String ajusteLink = link.attr("href");
                    ajusteLink = ajusteLink.substring(2,ajusteLink.length());
//                    agendaBeco203RS.add("http://www.beco203.com.br"+ajusteLink);

                    Evento evento = new Evento();
                    evento.setCapa("http://www.beco203.com.br"+ajusteImg);
                    evento.setUrl("http://www.beco203.com.br"+ajusteLink);

                    listaEventos.add(evento);
                }

                casa.setEventos(listaEventos);

            } catch (IOException e){
                e.printStackTrace();
            }
        }


        public void CasaDoLadoGetEachLinkDoInBackground(){
            /*try{
                Document document = Jsoup.connect("http://casadolado.com.br/").get();
                Elements links =  document.select("div.destaque-inicio > a[href~=(http://casadolado.com.br/)]");
                for(Element link : links){
//                    System.out.println(link.select("img[src~=(http://casadolado.com.br/wp-content/uploads/)]").attr("src"));
//                    agendaCasaDoLado.add(link.attr("href"));
                    listaEventos.add(link.attr("href"));
                }
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }

        @Override
        protected Void doInBackground(Void... params) {
            CuckoGetEachLinkDoInBackground();
            SinnersGetEachLinkDoInBackground();
            Beco203RSGetEachLinkDoInBackground();
            CasaDoLadoGetEachLinkDoInBackground();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            lv.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, agendaCucko));
//            lv.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, agendaSinners));
//            lv.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, agendaBeco203RS));
//            lv.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, agendaCasaDoLado));
            //lv.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaEventos));
            mProgressDialog.dismiss();


            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            MyAdapterMain adapter = new MyAdapterMain(MainActivity.this, casa.getEventos());
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }
}