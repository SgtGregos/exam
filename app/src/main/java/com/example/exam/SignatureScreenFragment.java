package com.example.exam;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.exam.databinding.FragmentSignatureScreenBinding;


public class SignatureScreenFragment extends Fragment {

    private FragmentSignatureScreenBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSignatureScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSubmitSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SignatureScreenFragment.this)
                        .navigate(R.id.action_signatureScreenFragment_to_SecondFragment);
            }
        });

        binding.buttonCancelSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SignatureScreenFragment.this)
                        .navigate(R.id.action_signatureScreenFragment_to_FirstFragment);
            }
        });

        binding.surface.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Do some drawing when surface is ready
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.RED);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

        });
}
}
