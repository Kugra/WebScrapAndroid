package br.com.webscrap;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.webscrap.adapter.MyAdapterMain;
import br.com.webscrap.model.Casa;
import br.com.webscrap.model.Evento;

public class MainActivity extends AppCompatActivity {

    private List<Casa> casas;

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog mProgressDialog;

    private Casa casa;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        casa = (Casa) getIntent().getSerializableExtra("extra");

        int codigoEscolha = casa.getCodigo();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch (codigoEscolha){

            case 0:
                new AgendaBeco203().execute();
                break;
            case 1:
                new AgendaCasaDoLado().execute();
                break;
            case 2:
                new AgendaCucko().execute();
                break;
            case 3:
                new AgendaSinners().execute();
                break;
        }
    }

    private class AgendaBeco203 extends AsyncTask<Void, Void, Void> {

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

                    String ajusteLink = link.attr("href");
                    ajusteLink = ajusteLink.substring(2,ajusteLink.length());

                    Evento evento = new Evento();

                    String capa = "http://www.beco203.com.br" + ajusteImg;
                    String url = "http://www.beco203.com.br" + ajusteLink;

                    evento.setCapa(capa);
                    evento.setUrl(url);
                    evento.setCodigo(casa.getCodigo());

                    listaEventos.add(evento);

                }

                casa.setEventos(listaEventos);

            } catch (IOException e){

                e.printStackTrace();

            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            Beco203RSGetEachLinkDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            mProgressDialog.dismiss();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            MyAdapterMain adapter = new MyAdapterMain(MainActivity.this, casa.getEventos());

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        }
    }

    private class AgendaCasaDoLado extends AsyncTask<Void, Void, Void> {

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


        public void CasaDoLadoGetEachLinkDoInBackground(){

            try{

                Document document = Jsoup.connect("http://casadolado.com.br/").get();
                Elements links =  document.select("div.destaque-inicio > a[href~=(http://casadolado.com.br/)]");
                for(Element link : links){

                    String capa = link.select("img[src~=(http://casadolado.com.br/wp-content/uploads/)]").attr("src");
                    String url = link.attr("href");

                    Evento evento = new Evento();
                    evento.setCapa(capa);
                    evento.setUrl(url);
                    evento.setCodigo(casa.getCodigo());

                    listaEventos.add(evento);

                }

                casa.setEventos(listaEventos);

            } catch (IOException e){

                e.printStackTrace();

            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            CasaDoLadoGetEachLinkDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            mProgressDialog.dismiss();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            MyAdapterMain adapter = new MyAdapterMain(MainActivity.this, casa.getEventos());

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        }
    }

    private class AgendaCucko extends AsyncTask<Void, Void, Void> {

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

            try {

                Document document = Jsoup.connect("http://www.cucko.com.br/agenda/").get();
                Elements links = document.select("a[href~=(agenda/evento)]");

                for (Element link : links){

                    Evento evento = new Evento();

                    String url = "http://www.cucko.com.br/" + link.attr("href");
                    String capa = "http://www.cucko.com.br/" + link.select("img[src~=(uploads/eventos/imagem/)]").attr("src");

                    evento.setCapa(capa);
                    evento.setUrl(url);
                    evento.setCodigo(casa.getCodigo());

                    listaEventos.add(evento);

                }

                casa.setEventos(listaEventos);

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            CuckoGetEachLinkDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            mProgressDialog.dismiss();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            MyAdapterMain adapter = new MyAdapterMain(MainActivity.this, casa.getEventos());

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        }
    }

    private class AgendaSinners extends AsyncTask<Void, Void, Void> {

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

        public void SinnersGetEachLinkDoInBackground(){

            try{

                Document document = Jsoup.connect("http://www.sinnersclub.com.br").get();
                Elements links =  document.select("a[href~=(/agenda/)]");

                for(Element link : links){

                    Evento evento = new Evento();

                    String url = link.attr("href");
                    String capa = "http://www.sinnersclub.com.br" + link.attr("cover-image");

                    evento.setUrl(url);
                    evento.setCapa(capa);
                    evento.setCodigo(casa.getCodigo());

                    listaEventos.add(evento);

                }

                casa.setEventos(listaEventos);

            } catch (IOException e){

                e.printStackTrace();

            }
        }


        @Override
        protected Void doInBackground(Void... params) {

            SinnersGetEachLinkDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

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