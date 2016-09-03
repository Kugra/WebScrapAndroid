package br.com.webscrap;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.view.Window;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import br.com.webscrap.model.Evento;

public class InfoActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        evento = (Evento) getIntent().getSerializableExtra("extra");

        int codigoEscolha = evento.getCodigo();

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

        SpannableString s;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(InfoActivity.this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setTitle("Atenção");
            mProgressDialog.setMessage("Aguarde, carregando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        public void Beco203RSGetPartyInfoDoInBackground(){

            try {

                Document document = Jsoup.connect(evento.getUrl()).get();
                Elements infoEvento = document.select("div#textoAgendaInterna");

                for (Element info : infoEvento){

                    s = new SpannableString(Html.fromHtml(info.html()));

                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }


        @Override
        protected Void doInBackground(Void... params) {

            Beco203RSGetPartyInfoDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void v) {

            if(s == null || s.toString().isEmpty()) return;

            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(s);

            mProgressDialog.dismiss();

        }
    }

    private class AgendaCasaDoLado extends AsyncTask<Void, Void, Void> {

        SpannableString s;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(InfoActivity.this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setTitle("Atenção");
            mProgressDialog.setMessage("Aguarde, carregando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        public void CasaDoLadoGetPartyInfoDoInBackground(){

            try {

                Document document = Jsoup.connect(evento.getUrl()).get();
                Elements infoEvento = document.select("div[id*=-0-1]");

                for (Element info : infoEvento){

                    s = new SpannableString(Html.fromHtml(info.html()));

                }
            } catch (IOException e) {

                e.printStackTrace();

            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            CasaDoLadoGetPartyInfoDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void v) {

            if(s == null || s.toString().isEmpty()) return;

            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(s);

            mProgressDialog.dismiss();
        }
    }

    private class AgendaCucko extends AsyncTask<Void, Void, Void> {

        SpannableString s;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(InfoActivity.this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setTitle("Atenção");
            mProgressDialog.setMessage("Aguarde, carregando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        public void CuckoGetPartyInfoDoInBackground(){

            try {

                Document document = Jsoup.connect(evento.getUrl()).get();
                Elements infoEvento = document.select("div#info-evento");

                for (Element info : infoEvento){

                    s = new SpannableString(Html.fromHtml(info.html()));

                }
            } catch (IOException e) {

                e.printStackTrace();

            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            CuckoGetPartyInfoDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void v) {

            if(s == null || s.toString().isEmpty()) return;

            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(s);

            mProgressDialog.dismiss();

        }
    }

    private class AgendaSinners extends AsyncTask<Void, Void, Void> {

        SpannableString s;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(InfoActivity.this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setTitle("Atenção");
            mProgressDialog.setMessage("Aguarde, carregando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        public void SinnersGetPartyInfoDoInBackground() {

            try {

                Document document = Jsoup.connect(evento.getUrl()).get();
                Elements infoEvento = document.select("div.content");

                for (Element info : infoEvento) {

                    s = new SpannableString(Html.fromHtml(info.html()));

                }
            } catch (IOException e) {

                e.printStackTrace();

            }
        }


        @Override
        protected Void doInBackground(Void... params) {

            SinnersGetPartyInfoDoInBackground();

            return null;

        }

        @Override
        protected void onPostExecute(Void v) {

            if(s == null || s.toString().isEmpty()) return;

            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(s);

            mProgressDialog.dismiss();

        }
    }
}
